module com.example.utilityproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.utilityproject to javafx.fxml;
    exports com.example.utilityproject;
}