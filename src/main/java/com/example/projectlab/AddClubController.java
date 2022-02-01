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
import models.Club;
import models.League;
import models.Manager;
import models.Stadium;
import models.enums.Objects;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddClubController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<League> leagueChoiceBox;

    @FXML
    private ChoiceBox<Stadium> stadiumChoiceBox;

    @FXML
    private ChoiceBox<Manager> managerChoiceBox;


    public void switchToHomeView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        this.setStageAndScene(event);
    }

    public void save(ActionEvent event) throws IOException {
        String clubName = nameTextField.getText();
        League selectedLeague = leagueChoiceBox.getSelectionModel().getSelectedItem();
        Stadium selectedStadium = stadiumChoiceBox.getSelectionModel().getSelectedItem();
        Manager selectedManager = managerChoiceBox.getSelectionModel().getSelectedItem();

        Club newClub = new Club(UUID.randomUUID(), clubName, LocalDateTime.now(), selectedStadium.getId(), selectedLeague.getId(), selectedManager.getId());
        Db.getInstance().setData(Objects.Club, newClub);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();
        switchToHomeView(event);
    }

    private void setStageAndScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leagueChoiceBox.getItems().addAll(Db.getInstance().getData(Objects.League));
        stadiumChoiceBox.getItems().addAll(Db.getInstance().getData(Objects.Stadium));
        managerChoiceBox.getItems().addAll(Db.getInstance().getData(Objects.Manager));
    }
}
