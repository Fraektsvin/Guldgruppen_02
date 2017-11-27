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

public class GameViewController implements Initializable {

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
    private Button playerWallet;
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
    private void playerWalletAction(ActionEvent event) {
        textConsole.appendText(business.printWallet());
    }

    @FXML
    private void playerGetAction(ActionEvent event) {
        textConsole.appendText(business.getCoin("penge"));
    }

    @FXML
    private void playerInteractAction(ActionEvent event) {
        textConsole.appendText(business.interactWith(business.whichNPC()));
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
        System.exit(0);
    }

    private void processDirection(String directions) {
        if (!directions.contains("west")) {
            disableDirection("west");
        } else if (!directions.contains("east")) {
            disableDirection("east");
        } else if (!directions.contains("north")) {
            disableDirection("north");
        } else if (!directions.contains("south")) {
            disableDirection("south");
        }
    }

    private void disableDirection(String direction) {

        switch (direction) {
            case "west":
                goWest.setDisable(true);
                break;
            case "east":
                goEast.setDisable(true);
                break;
            case "north":
                goNorth.setDisable(true);
                break;
            case "south":
                goSouth.setDisable(true);
                break;
            default:
                goWest.setDisable(false);
                goEast.setDisable(false);
                goNorth.setDisable(false);
                goSouth.setDisable(false);
        }
    }
}