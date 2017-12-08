package GUI;

import Acquaintance.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    private Stage window;
    private IBusiness business;
    private double xOffset;
    private double yOffset;        
            

    @FXML
    private Button start;
    @FXML
    private Button instruction;
    @FXML
    private Button highScore;
    @FXML
    private AnchorPane Drag;
    @FXML
    private AnchorPane Topbar;
    @FXML
    private Label Overskrift;
    @FXML
    private Button Quit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        business = GameGUI.getInstance().getBusiness();
    }

    @FXML
    public void start(ActionEvent event) throws IOException  {
        Parent nextView = FXMLLoader.load(getClass().getResource("Introduktion.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        newScene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.show();
        nextView.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });
        nextView.setOnMouseDragged((MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });
    }

    public void HighScore(ActionEvent event) {
         
    }

    @FXML
    private void playerQuitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void InstructionAction(ActionEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("Instruktions.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        newScene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.show();
          nextView.setOnMousePressed((MouseEvent event1) -> {
              xOffset = event1.getSceneX();
              yOffset = event1.getSceneY();
        });
        nextView.setOnMouseDragged((MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });
    }
}
