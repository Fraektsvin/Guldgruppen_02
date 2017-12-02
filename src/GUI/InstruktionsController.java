package GUI;

import java.awt.event.MouseEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InstruktionsController implements Initializable {

    @FXML
    private ImageView btn_settings, btn_user;
    @FXML
    private AnchorPane h_settings, h_user;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void handleButtonAction(MouseEvent event) {
    }

    private void returner(ActionEvent event) throws IOException {
        Parent nextView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

}
