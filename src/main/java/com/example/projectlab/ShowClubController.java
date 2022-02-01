package com.example.projectlab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Club;
import models.Player;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowClubController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Club currentClub;

    @FXML
    private ListView<Player> PlayersListView;

    @FXML
    private Label ClubNameLabel;
    @FXML
    private Label CreationTimeLabel;
    @FXML
    private Label IdLabel;
    @FXML
    private Label StadiumIdLabel;
    @FXML
    private Label ManagerIdLabel;
    @FXML
    private Label LeagueIdLabel;

    private void setLabels() {
        ClubNameLabel.setText(currentClub.toString());
        CreationTimeLabel.setText(currentClub.getCreationTime().toString());
        IdLabel.setText(currentClub.getId().toString());
        StadiumIdLabel.setText(currentClub.getStadiumId().toString());
        ManagerIdLabel.setText(currentClub.getManagerId().toString());
        LeagueIdLabel.setText(currentClub.getLeagueId().toString());
    }

    private void setListView() {
        List<Player> players = Db.getInstance().getPlayersByClub(currentClub);
        PlayersListView.getItems().setAll(players);
    }

    public void setClub(Club club) {
        currentClub = club;
        setLabels();
        setListView();
    }

    public void switchToLeagueView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show-league.fxml"));
        root = loader.load();
        ShowLeagueController controller = loader.getController();
        controller.setLeague(currentClub.getLeague());
        this.setStageAndScene(event);
    }

    private void setStageAndScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShowPlayer(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show-player.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        var currentPlayer = PlayersListView.getSelectionModel().getSelectedItem();
        scene = new Scene(loader.load());
        ShowPlayerController controller = loader.getController();
        controller.setPlayer(currentPlayer);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayersListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    try {
                        switchToShowPlayer(click);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        IdLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                var content = new ClipboardContent();
                content.putString(IdLabel.getText());
                Clipboard.getSystemClipboard().setContent(content);
            }
        });
    }
}
