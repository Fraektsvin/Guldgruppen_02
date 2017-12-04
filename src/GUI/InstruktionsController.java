package GUI;

import Acquaintance.IBusiness;
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

    IBusiness business;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        business = GameGUI.getInstance().getBusiness();
    }
    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) throws IOException {
        if (event.getSource() == btn_direction) {
            h_direction.setVisible(true);
            h_wallet.setVisible(false);
            h_interaction.setVisible(false);
        } else if (event.getSource() == btn_wallet) {
            h_wallet.setVisible(true);
            h_direction.setVisible(false);
            h_interaction.setVisible(false);
        } else if (event.getSource() == btn_interaction) {
            h_interaction.setVisible(true);
            h_direction.setVisible(false);
            h_interaction.setVisible(false);
        } else if (event.getSource() == btn_return) {
            Parent nextView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Scene newScene = new Scene(nextView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.show();
        }
    }
}
