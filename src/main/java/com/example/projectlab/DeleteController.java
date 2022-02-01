package com.example.projectlab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.enums.Objects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class DeleteController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public ChoiceBox<Objects> objectsChoiceBox;
    @FXML
    public TextField IdTextField;

    public void switchToHomeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        this.setStageAndScene(event);
    }

    public void delete(ActionEvent event) {
        UUID idToDelete;
        try {
            idToDelete = UUID.fromString(IdTextField.getText());
            var selected = objectsChoiceBox.getSelectionModel().getSelectedItem();
            Db.getInstance().deleteData(selected,idToDelete);
            switchToHomeView(event);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error during delete", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
            }
            IdTextField.clear();
            return;
        }
    }

    private void setStageAndScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        objectsChoiceBox.getItems().setAll(Objects.values());
    }
}
