module com.example.nushhack23 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.nushhack23 to javafx.fxml;
    opens com.example.nushhack23.controller to javafx.fxml;
    opens com.example.nushhack23.model to javafx.base;
    exports com.example.nushhack23;
}