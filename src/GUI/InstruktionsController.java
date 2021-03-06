package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InstruktionsController implements Initializable {
    
    private double xOffset;
    private double yOffset;

    @FXML
    private ImageView btn_direction;

    @FXML
    private ImageView btn_wallet;
    @FXML
    private ImageView btn_interaction;
    @FXML
    private ImageView btn_return;
    @FXML
    private AnchorPane h_direction;
    @FXML
    private AnchorPane h_wallet;
    @FXML
    private AnchorPane h_interaction;
    @FXML
    private ImageView btn_directions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws IOException {
        if (event.getSource() == btn_direction) {
            h_direction.toFront();
        } else if (event.getSource() == btn_wallet) {
            h_wallet.toFront();
        } else if (event.getSource() == btn_interaction) {
            h_interaction.toFront();
        } else if (event.getSource() == btn_return) {
            Parent nextView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Scene newScene = new Scene(nextView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.show();
            nextView.setOnMousePressed((javafx.scene.input.MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });
        nextView.setOnMouseDragged((javafx.scene.input.MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });
        }
    }

    private void returner(ActionEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
        nextView.setOnMousePressed((javafx.scene.input.MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });
        nextView.setOnMouseDragged((javafx.scene.input.MouseEvent event1) -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });
    }

    @FXML
    private void OnTarget(MouseEvent event) {
    }

}
