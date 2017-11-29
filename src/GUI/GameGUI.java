package GUI;

import Acquaintance.IBusiness;
import Acquaintance.IGame;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameGUI extends Application implements IGame {

    private static GameGUI gui; //SINGLETON 
    private Scene mainView;
    private Scene gameView;
    
    private Scene loadScene(String filename) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
        Parent root = loader.load();
        return new Scene(root);
    }

    IBusiness game; //INTERFACET til at kalde metoder fra facaden

    // En metode vi kan kalde fra andre klasser, som returnerer sig selv (GameGUI)
    public static GameGUI getInstance() {
        return gui;
    }

    @Override
    public void start(Stage stage) throws Exception {      
        //this.gameView = loadScene("GameView.fxml" );
        Parent p = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(p);
        //mainView.setFill(javafx.scene.paint.Color.TRANSPARENT);
        //stage.initStyle(StageStyle.TRANSPARENT);              
        stage.setScene(scene);
        stage.show();
  
    }
    
    //Bliver kaldt af STARTER (glue code), som realiserer interfacet IBusiness
    @Override
    public void setBusiness(IBusiness business) {
        game = business;
    }
    

    //Returnerer IBusiness som blev sat af Starter(Glue coden) - Bliver kaldt i FXMLController
    public IBusiness getBusiness() {
        return game;
    }

    //Starter spillet op, og instantierer sig selv
    @Override
    public void startApplication(String[] args) {
        gui = this;
        launch(args);
    }

}
