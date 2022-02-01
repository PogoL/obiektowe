package com.example.projectlab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Player;

import java.io.IOException;

public class ShowPlayerController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Player currentPlayer;

    @FXML
    private Label PlayerNameLabel;
    @FXML
    private Label CreationTimeLabel;
    @FXML
    private Label IdLabel;
    @FXML
    private Label MarketValueLabel;
    @FXML
    private Label ClubIdLabel;

    private void setLabels() {
        PlayerNameLabel.setText(currentPlayer.toString());
        CreationTimeLabel.setText(currentPlayer.getCreationTime().toString());
        IdLabel.setText(currentPlayer.getId().toString());
        MarketValueLabel.setText(String.valueOf(currentPlayer.getMarketValue()));
        ClubIdLabel.setText(currentPlayer.getClubId().toString());
    }

    public void setPlayer(Player player) {
        currentPlayer = player;
        setLabels();
    }

    public void switchToClubView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show-club.fxml"));
        root = loader.load();
        ShowClubController controller = loader.getController();
        controller.setClub(currentPlayer.getClub());
        this.setStageAndScene(event);
    }

    private void setStageAndScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
