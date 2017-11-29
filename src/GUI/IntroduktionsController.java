
package GUI;

import Acquaintance.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IntroduktionsController implements Initializable {

    IBusiness business;

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
    private void text(ActionEvent event) throws IOException {
        String textAreaString = business.printWelcome();
        this.textFieldInput.setText(textAreaString);
        
        
    }
   
    }
