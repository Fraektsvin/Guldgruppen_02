package GUI;

import Acquaintance.IBusiness;
import Acquaintance.IGame;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameGUI extends Application implements IGame {

    private static GameGUI gui;
    private double xOffset = 0;
    private double yOffset = 0;

    IBusiness game; //INTERFACET til at kalde metoder fra facaden

    // En metode vi kan kalde fra andre klasser, som returnerer sig selv (GameGUI)
    public static GameGUI getInstance() {
        return gui;
    }

    @Override
    public void start(Stage stage) throws Exception {      
        Parent p = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        p.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        p.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX()- xOffset);
            stage.setY(event.getScreenY()- yOffset);
        });
        Scene scene = new Scene(p);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
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
