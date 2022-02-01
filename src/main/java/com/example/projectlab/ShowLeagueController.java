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
import models.League;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowLeagueController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private League currentLeague;

    @FXML
    private ListView<Club> ClubListView;

    @FXML
    private Label LeagueNameLabel;
    @FXML
    private Label CreationTimeLabel;
    @FXML
    private Label IdLabel;

    private void setLabels() {
        LeagueNameLabel.setText(currentLeague.toString());
        CreationTimeLabel.setText(currentLeague.getCreationTime().toString());
        IdLabel.setText(currentLeague.getId().toString());
    }

    private void setListView() {
        List<Club> clubs = Db.getInstance().getClubsByLeague(currentLeague);
        ClubListView.getItems().setAll(clubs);
    }

    public void switchToShowClub(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("show-club.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        var currentClub = ClubListView.getSelectionModel().getSelectedItem();
        scene = new Scene(loader.load());
        ShowClubController controller = loader.getController();
        controller.setClub(currentClub);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClubListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    try {
                        switchToShowClub(click);
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

    public void setLeague(League league) {
        currentLeague = league;
        setLabels();
        setListView();
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
