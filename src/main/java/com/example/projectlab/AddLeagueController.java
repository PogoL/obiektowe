package com.example.projectlab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.League;
import models.enums.Country;
import models.enums.Objects;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddLeagueController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<Country> countryChoiceBox;

    public void save(ActionEvent event) throws IOException {
        String leagueName = nameTextField.getText();
        League newLeague = new League(UUID.randomUUID(), leagueName, LocalDateTime.now(), countryChoiceBox.getValue());
        Db.getInstance().setData(Objects.League, newLeague);

        switchToHomeView(event);
    }

    private void setStageAndScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        this.setStageAndScene(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryChoiceBox.getItems().addAll(Arrays.asList(Country.values()));
    }
}
