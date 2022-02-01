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
import models.Club;
import models.League;
import models.Manager;
import models.enums.Objects;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AddManagerController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField yearsOfExpTextField;

    public void save(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String lastName = lastNameTextField.getText();
        String ageText = ageTextField.getText();
        String yearsOfExpText = yearsOfExpTextField.getText();

        int ageNumber;
        try {
            ageNumber = Integer.parseInt(ageText);
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot convert: '" + ageText + "' to number!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
            }
            ageTextField.clear();
            return;
        }
        int yearsOfExpNumber;
        try {
            yearsOfExpNumber = Integer.parseInt(yearsOfExpText);
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot convert: '" + yearsOfExpText + "' to number!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
            }
            yearsOfExpTextField.clear();
            return;
        }

        Manager newManager = new Manager(UUID.randomUUID(), name, LocalDateTime.now(), lastName, ageNumber, yearsOfExpNumber);
        Db.getInstance().setData(Objects.Manager, newManager);

        switchToHomeView(event);
    }

    public void switchToHomeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        this.setStageAndScene(event);
    }

    private void setStageAndScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

