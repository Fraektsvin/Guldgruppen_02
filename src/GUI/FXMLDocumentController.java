package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Button goNorth;
    @FXML
    private Button goWest;
    @FXML
    private Button goSouth;
    @FXML
    private Button goEast;
    @FXML
    private Button playerInventory;
    @FXML
    private Button playerPengepung;
    @FXML
    private Button playerGet;
    @FXML
    private Button playerInteract;
    @FXML
    private Button playerHelp;
    @FXML
    private Button playerSave;
    @FXML
    private Button playerLoad;
    @FXML
    private Button playerQuit;
    @FXML
    private TextArea textConsole;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goNorthAction(ActionEvent event) {
        textConsole.appendText("You went north\n");
    }

    @FXML
    private void goWestAction(ActionEvent event) {
        textConsole.appendText("You went west\n");
    }

    @FXML
    private void goSouthAction(ActionEvent event) {
        textConsole.appendText("You went south\n");
    }

    @FXML
    private void goEastAction(ActionEvent event) {
        textConsole.appendText("You went east\n");
    }

    @FXML
    private void playerInventoryAction(ActionEvent event) {
    }

    @FXML
    private void playerPengepungAction(ActionEvent event) {
    }

    @FXML
    private void playerGetAction(ActionEvent event) {
    }

    @FXML
    private void playerInteractAction(ActionEvent event) {
    }

    @FXML
    private void playerHelpAction(ActionEvent event) {
    }

    @FXML
    private void playerSaveAction(ActionEvent event) {
    }

    @FXML
    private void playerLoadAction(ActionEvent event) {
    }

    @FXML
    private void playerQuitAction(ActionEvent event) {
    }
}
