package com.example.utilityproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CodeTemplate {

    public static void setTemplate(String location, String txt) {
        try {
            Path filePath = Path.of(location);

            List<String> lines = Files.readAllLines(filePath);

            List<String> modifiedLines = lines.stream()
                    .map(line -> line.replace("// Automate here -1", txt+"\n // Automate here -1"))
                    .collect(Collectors.toList());

            Files.write(filePath, modifiedLines, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.err.println("An error occurred while reading or writing to the file: " + e.getMessage());
            // errorRootPathTxt.setText("Something went wrong!");
        }
    }





}
