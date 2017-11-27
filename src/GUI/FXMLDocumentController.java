package GUI;

import Acquaintance.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {

    IBusiness business;

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

    //Brug af SINGLETON design pattern
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = GameGUI.getInstance().getBusiness();
    }

    @FXML
    private void goNorthAction(ActionEvent event) {
        //textConsole.appendText("You went north\n");
        String textReturned = business.goToDirection("north");
        textConsole.appendText(textReturned);
    }

    @FXML
    private void goWestAction(ActionEvent event) {
        //textConsole.appendText("You went west\n");
        String textReturned = business.goToDirection("west");
        textConsole.appendText(textReturned);
    }

    @FXML
    private void goSouthAction(ActionEvent event) {
        //textConsole.appendText("You went south\n");
        String textReturned = business.goToDirection("south");
        textConsole.appendText(textReturned);
    }

    @FXML
    private void goEastAction(ActionEvent event) {
        //textConsole.appendText("You went east\n");
        String textReturned = business.goToDirection("east");
        textConsole.appendText(textReturned);
    }

    @FXML
    private void playerInventoryAction(ActionEvent event) {
        textConsole.appendText(business.printInventory());
    }

    @FXML
    private void playerPengepungAction(ActionEvent event) {
        textConsole.appendText(business.printPengepung());
    }

    @FXML
    private void playerGetAction(ActionEvent event) {
        textConsole.appendText(business.printWelcome());
    }

    @FXML
    private void playerInteractAction(ActionEvent event) {
    }

    @FXML
    private void playerHelpAction(ActionEvent event) {
        textConsole.appendText(business.printHelp());
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
