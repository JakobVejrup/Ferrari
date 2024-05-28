package com.presentation.tools.alert.csvwriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    
    private String path;
    private String[] headers;
    private String[][] content;

    public CSVWriter(String path, String[] headers, String[][] content) {
        this.path = path;
        this.headers = headers;
        this.content = content;
    }

    private void writeRow(FileWriter writer, String[] row) throws IOException {
        String rowString = String.join(";", row);
        writer.write(rowString);
        writer.write("\n");
    }

    public void write() {
        File file = new File(path + ".csv");
        

        try (FileWriter writer = new FileWriter(file)) {
            writeRow(writer, headers);
            for (String[] row : content) {
                writeRow(writer, row);
            }
            writer.close();

            System.out.println("CSV file has been written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
