package GUI;

import Acquaintance.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    Stage window;
    IBusiness business;

    private Scene mainView;
    private Scene gameView;

    @FXML
    private Label label;
    @FXML
    private Button start;
    @FXML
    private Button instruction;
    @FXML
    private Button highScore;
    @FXML
    private Button quit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        business = GameGUI.getInstance().getBusiness();
    }

    @FXML
    public void start(ActionEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    public void Instruction(ActionEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("Instruktions.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();

    }

    @FXML
    public void HighScore(ActionEvent event) {
        highScore.setOnAction((EventHandler<ActionEvent>) gameView);

    }

    @FXML
    private void playerQuitAction(ActionEvent event) {
        System.exit(0);
    }

}
