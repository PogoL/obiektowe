package com.example.projectlab;

import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import models.Club;
import models.Manager;
import models.Player;
import models.enums.Objects;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddPlayerController implements Initializable {
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
    private TextField marketValueTextField;

    @FXML
    private ChoiceBox<Club> clubChoiceBox;

    public void save(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String lastName = lastNameTextField.getText();
        String ageText = ageTextField.getText();
        String marketValueText = marketValueTextField.getText();

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
        double yearsOfExpNumber;
        try {
            yearsOfExpNumber = Double.parseDouble(marketValueText);
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot convert: '" + marketValueText + "' to number!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
            }
            marketValueTextField.clear();
            return;
        }

        Club selectedClub = clubChoiceBox.getSelectionModel().getSelectedItem();
        Player newPlayer = new Player(UUID.randomUUID(), name, LocalDateTime.now(), lastName, ageNumber, yearsOfExpNumber, selectedClub.getId());
        Db.getInstance().setData(Objects.Player, newPlayer);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clubChoiceBox.getItems().setAll(Db.getInstance().getData(Objects.Club));
    }
}

