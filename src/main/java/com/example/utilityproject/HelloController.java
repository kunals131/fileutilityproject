package com.example.utilityproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.text.MessageFormat;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class HelloController implements Initializable {

    private String[] toggleStateOptions = {"ready_for_release", "wip", "accepted"};
    private String[] toggleTypeOptions = {"RELEASE", "BUSINESS"};

    public static String generateSlug(String input) {
        String slug = input.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "_");
        return slug;
    }

    @FXML
    private TextField androidvTF;

    @FXML
    private TextField cpcsTF;

    @FXML
    private TextField customDescTF;

    @FXML
    private TextField customValueTF;

    @FXML
    private TextArea descriptionTF;

    @FXML
    private DatePicker endDateDF;

    @FXML
    private Text errorRootPathTxt;

    @FXML
    private CheckBox featureActiveCB;

    @FXML
    private Pane formStep1;

    @FXML
    private Pane formStep2;

    @FXML
    private TextField iosvTF;

    @FXML
    private TextField ipadvTF;

    @FXML
    private Pane landing;

    @FXML
    private TextField rootpath;

    @FXML
    private CheckBox sessionModifiableCB;

    @FXML
    private DatePicker startDateDF;

    @FXML
    private Button submitbtn;

    @FXML
    private Button toggleExtensionSubmitBTN;

    @FXML
    private TextField toggleNameTF;

    @FXML
    private TextField toggleServiceNameTF;

    @FXML
    private ChoiceBox<String> toggleStateTF;

    @FXML
    private ChoiceBox<?> toggleStoriesTF;

    @FXML
    private Button toggleSubmitBTN;

    @FXML
    private TextField toggleTypeTF;
    @FXML
    void handleCreateToggle(ActionEvent event) {

        String txt = MessageFormat.format("{0}:\nstate:{1}\ndescription:{2}\nstories:\n{3}\nx-toggle-extensions:", toggleNameTF.getText(),toggleStateTF.getValue(), descriptionTF.getText(),toggleStoriesTF.getValue());
        String location = paths.getAbsolutePath(rootpath.getText(), paths.MobileYAMLFilePath);
        try {
            Path filePath = Path.of(location);

            List<String> lines = Files.readAllLines(filePath);

            List<String> modifiedLines = lines.stream()
                    .map(line -> line.replace("// Automate here -1", txt+"\n // Automate here -1"))
                    .collect(Collectors.toList());

            Files.write(filePath, modifiedLines, StandardOpenOption.TRUNCATE_EXISTING);

            landing.setVisible(false);
            formStep1.setVisible(false);
            formStep2.setVisible(false);
        } catch (IOException e) {
            System.err.println("An error occurred while reading or writing to the file: " + e.getMessage());
            errorRootPathTxt.setText("Something went wrong!");
        }
    }


    @FXML
    void handleNameInputChange(InputMethodEvent event) {

    }

    @FXML
    void handleRootPathSubmitClick(ActionEvent event) {
        // Resetting the error
        errorRootPathTxt.setText("");
        errorRootPathTxt.setVisible(false);
        Boolean ifPathExists = paths.checkIfPathExists(rootpath.getText());

        if (ifPathExists) {
            // Setting view's visibility
            landing.setVisible(false);
            formStep2.setVisible(false);
            formStep1.setVisible(true);
        }
        else {
            // setting error for wrong path
            errorRootPathTxt.setText("Please enter  a valid path");
            errorRootPathTxt.setVisible(true);
        }


    }

    @FXML
    void handleToggleFormOneSubmit(ActionEvent event) {

        System.out.println(toggleNameTF.getText());
        System.out.println(toggleStateTF.getValue());
        System.out.println(toggleStoriesTF.getValue());
        System.out.println(descriptionTF.getText());

        landing.setVisible(false);
        formStep1.setVisible(false);
        formStep2.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleStateTF.getItems().addAll(toggleStateOptions);
        landing.setVisible(true);
        formStep1.setVisible(false);
        formStep2.setVisible(false);

    }
}
