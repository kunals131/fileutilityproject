package com.example.utilityproject;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class paths {

    public static String getAbsolutePath(String rootPath, String relativePath) {
        return rootPath+relativePath;
    }

//    Relative paths of files to the root directory
    public static final String MobileYAMLFilePath = "\\war\\src\\main\\resources\\toggles\\mobile.yaml";
    public static final String ToggleHelperFilePath = "\\war\\src\\main\\java\\com\\barclaycardus\\svc\\mobile\\util\\toggles\\ToggleHelper.java";
    public static final String AccountConfigFacadeFilePath = "\\war\\src\\main\\java\\com\\barclaycardus\\svc\\mobile\\facade\\AccountConfigFacade.java";
    public static final String FeaturesFilePath = "\\war\\src\\main\\resources\\feature\\feature.yaml";
    public static final String FeatureControllerFilePath = "\\war\\src\\main\\java\\com\\barclaycardus\\svc\\mobile\\controller\\FeaturesController.java";
    public static final String MobileContextToggleFilePath = "\\war\\src\\main\\webapp\\WEB-INF\\mobile-context-toggles.xml";
    public static final String AccountConfigFacadeTestFilePath = "\\war\\src\\main\\java\\com\\barclaycardus\\svc\\mobile\\facade\\AccountConfigFacadeTest.java";
    public static final String ToggleEnumFilePath = "\\war\\src\\main\\java\\com\\barclaycardus\\svc\\mobile\\util\\toggles\\ToggleEnum.java";


    public static Boolean checkIfPathExists(String rootPath) {
        String mobileYamlPath = getAbsolutePath(rootPath, MobileYAMLFilePath);
        String toggleHelperFilePath = getAbsolutePath(rootPath, ToggleHelperFilePath);
        String accountConfigFacadeFilePath = getAbsolutePath(rootPath, AccountConfigFacadeFilePath);
        String featureFilePath = getAbsolutePath(rootPath, FeaturesFilePath);
        String featureControllerFilePath = getAbsolutePath(rootPath, FeatureControllerFilePath);
        String mobileContextToggleFilePath = getAbsolutePath(rootPath, MobileContextToggleFilePath);
        String accountConfigFacadeTestFilePath = getAbsolutePath(rootPath, AccountConfigFacadeTestFilePath);
        String toggleEnumFilePath = getAbsolutePath(rootPath,ToggleEnumFilePath);

        File mobileYamlFile = new File(mobileYamlPath);
        File toggleHelperFile = new File(toggleHelperFilePath);
        File accountConfigFacadeFile = new File(accountConfigFacadeFilePath);
        File featureFile = new File(featureFilePath);
        File featureControllerFile = new File(featureControllerFilePath);
        File mobileContextFile = new File(mobileContextToggleFilePath);
        File accountConfigFacadeTestFile = new File(accountConfigFacadeTestFilePath);
        File toggleEnumFile = new File(toggleEnumFilePath);

        System.out.println(mobileYamlFile.exists());
        System.out.println(mobileYamlPath);


        if (mobileYamlFile.exists() && accountConfigFacadeTestFile.exists() && toggleEnumFile.exists() && toggleHelperFile.exists() && accountConfigFacadeFile.exists() && featureFile.exists() && featureControllerFile.exists() && mobileContextFile.exists() && accountConfigFacadeFile.exists()) {
            return true;
        }
        return false;

    }
}
