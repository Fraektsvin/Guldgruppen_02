package GUI;

import Acquaintance.IBusiness;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WinViewController implements Initializable {

    IBusiness business;
    private double xOffset;
    private double yOffset;

    @FXML
    private TextArea textConsole;
    @FXML
    private Button scoreShow;
    @FXML
    private Button mainViewButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = GameGUI.getInstance().getBusiness();
    }

    @FXML
    private void scoreShowAction(ActionEvent event) {
        textConsole.appendText("Tillykke med sejren Erik Deluxe!\n"
                + "Du er nu officielt den mest swag person i Swag City.\n"
                + "Din score var " + business.score() + ", og en tid på " + business.time() + " sekunder tilbage.");
        scoreShow.setVisible(false);
    }

    @FXML
    private void mainViewButtonAction(ActionEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
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
