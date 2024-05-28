package com.presentation.tools.csvwriter;
import com.model.entities.Invoice;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

public class CSVWriterTest extends TestCase {

    public CSVWriterTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */

    public static Test suite()
    {
        return new TestSuite( CSVWriterTest.class );
    }


    public void testWrite() {
        String filename = "test_invoices";
        String path = "C:\\Users\\kehan\\Downloads\\";
        List<Invoice> invoices = Arrays.asList(
                new Invoice(1, Date.valueOf(LocalDate.of(2023, 1, 1)), Date.valueOf(LocalDate.of(2023, 1, 31)), 100, 50,
                        500, 450, "Some details"),
                new Invoice(2, Date.valueOf(LocalDate.of(2023, 2, 1)), Date.valueOf(LocalDate.of(2023, 2, 28)), 200, 75,
                        625, 500, "More details"));

        CSVWriterInvoices writeCSV = new CSVWriterInvoices(filename, path, invoices);
        writeCSV.WriteCSV();

        File file = new File(path, filename + ".csv");
        Assert.assertTrue(file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            assertEquals("NR;Dato Start;Dato slut;Plus;Minus;UltimoValue;PrimoPrice;Details", reader.readLine());
            Assert.assertEquals(reader.readLine(), "1;2023-01-01;2023-01-31;100.0;50.0;500.0;450.0;Some details");
            Assert.assertEquals(reader.readLine(), "2;2023-02-01;2023-02-28;200.0;75.0;625.0;500.0;More details");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(Paths.get(path, filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
