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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameViewController implements Initializable {

    IBusiness business;
    private double xOffset;
    private double yOffset; 

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
    @FXML
    private TextField textFieldInput;

    //Brug af SINGLETON design pattern
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = GameGUI.getInstance().getBusiness();
    }

    @FXML
    private void goNorthAction(ActionEvent event) {
        String textReturned = business.goToDirection("north");
        textConsole.appendText(textReturned);
        
    }

    @FXML
    private void goWestAction(ActionEvent event) {
        String textReturned = business.goToDirection("west");
        textConsole.appendText(textReturned);
    }

    @FXML
    private void goSouthAction(ActionEvent event) {
        String textReturned = business.goToDirection("south");
        textConsole.appendText(textReturned);
    }

    @FXML
    private void goEastAction(ActionEvent event) {
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
        String npcName = business.whichNPC();
        String textReturned = business.interactWith(npcName, textFieldInput.getText());
        textConsole.appendText(textReturned + System.lineSeparator());
        textFieldInput.clear();
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
    private void playerQuitAction(ActionEvent event) throws IOException {
        business.timerStop();
        Parent nextView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        newScene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.show();
        nextView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @FXML
            public void handle(MouseEvent event) {
                xOffset =event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        nextView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @FXML
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX()- xOffset);
                stage.setY(event.getScreenY()- yOffset);
            }
        });
    }
}
