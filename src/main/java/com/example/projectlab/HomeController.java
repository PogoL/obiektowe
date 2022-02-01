package com.example.projectlab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.BaseEntity;
import models.League;
import models.enums.Objects;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;


public class HomeController implements Initializable {
    @FXML
    ListView<League> LeagueListView;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToAddLeague(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-league.fxml"));
        setStageAndShow(root, (Node)event.getSource());
    }

    private void setStageAndShow(Parent root, Node source) {
        stage = (Stage)source.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddClub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-club.fxml"));
        setStageAndShow(root, (Node)event.getSource());
    }

    public void switchToAddStadium(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-stadium.fxml"));
        setStageAndShow(root, (Node)event.getSource());
    }

    public void switchToAddManager(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-manager.fxml"));
        setStageAndShow(root, (Node)event.getSource());
    }

    public void switchToShowLeague(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show-league.fxml"));
        Parent root = loader.load();
        var currentLeague = LeagueListView.getSelectionModel().getSelectedItem();
        ShowLeagueController controller = loader.getController();
        controller.setLeague(currentLeague);

        setStageAndShow(root, (Node)event.getSource());
    }

    public void switchToAddPlayer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-player.fxml"));
        setStageAndShow(root, (Node)event.getSource());
    }

    public void delete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
        setStageAndShow(root, (Node)event.getSource());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LeagueListView.getItems().setAll(Db.getInstance().getData(Objects.League));
        LeagueListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    try {
                        switchToShowLeague(click);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}