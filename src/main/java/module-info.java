module com.example.projectlab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.projectlab to javafx.fxml;
    exports com.example.projectlab;
    exports models;
    opens models to javafx.fxml;
}