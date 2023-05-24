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
    private Button backBtnForm1;

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
    private Button homeBtn;

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
    private Pane sucessScreen;

    @FXML
    private Button toggleExtensionSubmitBTN;

    @FXML
    private Button toggleExtensionSubmitBTN1;

    @FXML
    private TextField toggleNameTF;

    @FXML
    private TextField toggleServiceNameTF;

    @FXML
    private ChoiceBox<String> toggleStateTF;

    @FXML
    private TextField toggleStoryTF;

    @FXML
    private Button toggleSubmitBTN;

    @FXML
    private TextField toggleTypeTF;

    @FXML
    void handleBackBtnClickForm2(ActionEvent event) {
        landing.setVisible(false);
        formStep1.setVisible(true);
        formStep2.setVisible(false);
        sucessScreen.setVisible(false);
    }

    @FXML
    void handleClickAnotherToggle(ActionEvent event) {
        landing.setVisible(true);
        formStep1.setVisible(false);
        formStep2.setVisible(false);
        sucessScreen.setVisible(false);
        rootpath.setText("");
        toggleNameTF.setText("");
        toggleServiceNameTF.setText("");
        descriptionTF.setText("");
        toggleTypeTF.setText("");
        toggleStoryTF.setText("");
        toggleStateTF.setVisible(false);
        // rootpath.setText("");
    }

    @FXML
    void handlebackButtonForm1Click(ActionEvent event) {
        landing.setVisible(true);
        formStep1.setVisible(false);
        formStep2.setVisible(false);
        sucessScreen.setVisible(false);
    }
    @FXML
    void handleCreateToggle(ActionEvent event) {
        String toggleNameSlug = generateSlug(toggleNameTF.getText());

        // Mobile.yaml file
        String mobileYamlText = MessageFormat.format("{0}:\n    state: {1}\n    description: \n     stories:\n", toggleNameSlug,toggleStateTF.getValue(), descriptionTF.getText());
        String[] stories = toggleStoryTF.getText().split(",");
        for(String story : stories) {
            mobileYamlText+=MessageFormat.format("      - {0}\n", story);
        }
        mobileYamlText += MessageFormat.format("    x-toggle-extensions:\n", 1);
        if (sessionModifiableCB.getText() != null) {
            mobileYamlText += MessageFormat.format("        sessionModifiable: {0}\n", sessionModifiableCB.getText());
        }
        if (toggleTypeTF.getText() != null) {
            mobileYamlText += MessageFormat.format("        toggleType: {0}\n", toggleTypeTF.getText());
        }if (iosvTF.getText() != null) {
            mobileYamlText += MessageFormat.format("        iosAppVersion: {0}\n", iosvTF.getText());
        }if (ipadvTF.getText() != null) {
            mobileYamlText += MessageFormat.format("        ipadAppVersion: {0}\n", ipadvTF.getText());
        }if (androidvTF.getText() != null) {
            mobileYamlText += MessageFormat.format("        androidAppVersion: {0}\n", androidvTF.getText());
        }if (startDateDF.getValue() != null) {
            mobileYamlText += MessageFormat.format("        startDate: {0}\n", startDateDF.getValue().toString());
        }if (endDateDF.getValue() != null) {
            mobileYamlText += MessageFormat.format("        endDate: {0}\n", endDateDF.getValue().toString());
        }if (cpcsTF.getText() != null) {
            mobileYamlText += MessageFormat.format("        cpcs: {0}\n", cpcsTF.getText());
        }if (customDescTF.getText() != null) {
            mobileYamlText += MessageFormat.format("        customPropertyDesc: {0}\n", customDescTF.getText());
        }if (customValueTF.getText() != null) {
            mobileYamlText += MessageFormat.format("        customPropertyValue: {0}\n", customValueTF.getText());
        }
        String mobileYamlLocation = paths.getAbsolutePath(rootpath.getText(), paths.MobileYAMLFilePath);
        CodeTemplate.setTemplate(mobileYamlLocation,mobileYamlText);


        // Mobile Context Toggle
        String mobileContextToggleText = MessageFormat.format("<bean class=“com.barclaycardus.svc.mobile.util.toggles.ToggleItem” id=“id_{0}” >\n   <property name=“name” value=“{0}“/>\n   <property name=“on” value=“{1}“/>\n    <property name=“description” value=“{2}“/>\n   <property name=“storyId” value=“{3}“/>\n   <property name=“toggleType” value=“{4}“/>\n", toggleNameSlug, sessionModifiableCB.getText(), descriptionTF.getText(),  toggleStoryTF.getText(), toggleTypeTF.getText());
        if (cpcsTF.getText() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“cpcs” value=“{0}“/>\n", cpcsTF.getText());
        }
        if (iosvTF.getText() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“iosAppVersion” value=“{0}“/>\n", iosvTF.getText());
        }     if (ipadvTF.getText() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“ipadAppVersion”  value=“{0}“/>\n", ipadvTF.getText());
        }     if (androidvTF.getText() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“androidAppVersion”  value=“{0}“/>\n", androidvTF.getText());
        }     if (toggleServiceNameTF.getText() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“serviceNames”  value=“{0}“/>\n", toggleServiceNameTF.getText());
        }    if (customValueTF.getText() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“customPropertyValue”   value=“{0}“/>\n", customValueTF.getText());
        }    if (customDescTF.getText() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“customPropertyDesc”   value=“{0}“/>\n", customDescTF.getText());
        } if (startDateDF.getValue() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“startDate”    value=“{0}“/>\n", startDateDF.getValue().toString());
        } if (endDateDF.getValue() != null) {
            mobileContextToggleText += MessageFormat.format("   <property name=“endDate”   value=“{0}“/>\n", endDateDF.getValue().toString());
        }
        mobileContextToggleText+="</bean>\n";
        String mobileContextToggleLocation = paths.getAbsolutePath(rootpath.getText(), paths.MobileContextToggleFilePath);
        CodeTemplate.setTemplate(mobileContextToggleLocation,mobileContextToggleText);

        //toggleEnumFile
        String mobileToggleEnumText = MessageFormat.format("{0},\n", toggleNameSlug.toUpperCase());
        String mobileToggleEnumLocation = paths.getAbsolutePath(rootpath.getText(), paths.MobileContextToggleFilePath);
        CodeTemplate.setTemplate(mobileToggleEnumLocation,mobileToggleEnumText );

        //toggleHelper
        String mobileToggleHelperText = MessageFormat.format("public static final String {0}=\"{1}\";\n", toggleNameSlug.toUpperCase(), toggleNameSlug);
        String mobileToggleHelperLocation = paths.getAbsolutePath(rootpath.getText(), paths.ToggleHelperFilePath);
        CodeTemplate.setTemplate(mobileToggleHelperLocation,mobileToggleHelperText );

        //feature yaml file
        if (featureActiveCB.getText() == "true") {
            String featureYamlText = MessageFormat.format("    - featureToggleName: {0}\n",toggleNameSlug);
            String featureYamlLocation = paths.getAbsolutePath(rootpath.getText(), paths.FeaturesFilePath);
            CodeTemplate.setTemplate(featureYamlLocation,featureYamlText );
        }
        //account config facade
        String accountConfigFacadeFileText = MessageFormat.format("financialToolsVO.setManageAlertsFeatureToggleEnabled(isAppVersionSupported(userAppVersion, supportedAppVersion, ToggleHelper.{0}));", toggleNameSlug.toUpperCase());
        String accountConfigFacadeLocation = paths.getAbsolutePath(rootpath.getText(), paths.AccountConfigFacadeFilePath);
        CodeTemplate.setTemplate(accountConfigFacadeLocation,accountConfigFacadeFileText );
        // Logic to add text to all the files
        landing.setVisible(false);
        formStep1.setVisible(false);
        formStep2.setVisible(false);
        sucessScreen.setVisible(true);
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
            sucessScreen.setVisible(false);

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

        System.out.println(descriptionTF.getText());

        landing.setVisible(false);
        formStep1.setVisible(false);
        formStep2.setVisible(true);
        sucessScreen.setVisible(false);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleStateTF.getItems().addAll(toggleStateOptions);
        landing.setVisible(true);
        formStep1.setVisible(false);
        formStep2.setVisible(false);
        sucessScreen.setVisible(false);


    }
}
