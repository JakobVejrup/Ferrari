package com.presentation.tools.alert.csvwriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.model.entities.Invoice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CSVWriterTest {

    @Test
    public void testWrite() {
        String filename = "test_invoices.csv";
        String path = "test_output/";
        List<Invoice> invoices = Arrays.asList(
                new Invoice(1, Date.valueOf(LocalDate.of(2023, 1, 1)), Date.valueOf(LocalDate.of(2023, 1, 31)), 100, 50,
                        500, 450, "Some details"),
                new Invoice(2, Date.valueOf(LocalDate.of(2023, 2, 1)), Date.valueOf(LocalDate.of(2023, 2, 28)), 200, 75,
                        625, 500, "More details"));

        CSVWriterInvoices writeCSV = new CSVWriterInvoices(filename, path, invoices);
        writeCSV.WriteCSV();

        File file = new File(path + filename);
        assertTrue(file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            assertEquals("NR;Dato Start;Dato slut;Plus;Minus;UltimoValue;PrimoPrice;Details", reader.readLine());
            assertEquals("1;2023-01-01;2023-01-31;100.0;50.0;500.0;450.0;Some details", reader.readLine());
            assertEquals("2;2023-02-01;2023-02-28;200.0;75.0;625.0;500.0;More details", reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(Paths.get(path + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

 