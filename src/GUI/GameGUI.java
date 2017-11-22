package GUI;

import Acquaintance.IBusiness;
import Acquaintance.IGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameGUI extends Application implements IGame {

    private static GameGUI gui; //SINGLETON 
    IBusiness game; //INTERFACET til at kalde metoder fra facaden

    // En metode vi kan kalde fra andre klasser, som returnerer sig selv (GameGUI)
    public static GameGUI getInstance() {
        return gui;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDOcument.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    //Bliver kaldt af STARTER (glue code), som realiserer interfacet IBusiness
    @Override
    public void setGame(IBusiness business) {
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
