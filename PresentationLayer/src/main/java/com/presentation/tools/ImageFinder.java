package com.presentation.tools;

import java.io.File;
import java.nio.file.Files;

import com.presentation.tools.alert.Alerter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageFinder {
        public static byte[] findImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vælg et billede");

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Billeder png, jpg, jpeg, gif", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(stage);

        try {
            return Files.readAllBytes(file.toPath());
        }
        catch(Exception e) {
            Alerter.information("Billede fejl", "Filen er ikke et tilgængeligt billede");
            return null;
        }

    }
}
