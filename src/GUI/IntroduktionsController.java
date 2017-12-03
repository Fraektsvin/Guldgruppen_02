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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class IntroduktionsController implements Initializable {

    IBusiness business;
    private double xOffset;
    private double yOffset;

    @FXML
    private TextArea textConsole;
    @FXML
    private Button Action;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = GameGUI.getInstance().getBusiness();
        String textAreaString = business.printWelcome();
        this.textConsole.setText(textAreaString);
    }

    @FXML
    private void Action(ActionEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
        business = GameGUI.getInstance().getBusiness();
        business.timerStart();
        nextView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @FXML
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        nextView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @FXML
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX()- xOffset);
                stage.setY(event.getScreenY()- yOffset);
            }
        });
    }
}
