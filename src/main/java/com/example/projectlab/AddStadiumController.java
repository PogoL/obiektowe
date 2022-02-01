package com.example.projectlab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Stadium;
import models.enums.Objects;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AddStadiumController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField capacityField;

    public void switchToHomeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        this.setStageAndScene(event);
    }

    public void save(ActionEvent event) throws IOException {
        String stadiumName = nameTextField.getText();
        String capacityText = capacityField.getText();

        int capacityNumber;
        try {
            capacityNumber = Integer.parseInt(capacityText);
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot convert: '" + capacityText + "' to number!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
            }
            capacityField.clear();
            return;
        }
        Stadium newStadium = new Stadium(UUID.randomUUID(), stadiumName, LocalDateTime.now(), capacityNumber);
        Db.getInstance().setData(Objects.Stadium, newStadium);

        switchToHomeView(event);
    }

    private void setStageAndScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

