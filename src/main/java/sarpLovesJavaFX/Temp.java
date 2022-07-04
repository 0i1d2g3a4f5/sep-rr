package sarpLovesJavaFX;

import client_package.client_gamelogic.game_elements.GameElement;
import client_package.client_gamelogic.game_elements.Laser;
import client_package.client_gamelogic.map.GameBoard;
import client_package.client_gamelogic.map.GameField;
import client_package.client_gamelogic.map.ModelLoader;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Temp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage stage1= new Stage();
        ModelLoader modelLoader = new ModelLoader();
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();

        GridPane gridPane = new GridPane();
        javaFXGridHandler.gridPaneFromGameBoard(modelLoader.loadMap("dizzy_highway"), gridPane);
        Scene scene = new Scene(gridPane, 1280, 720);
        stage1.setScene(scene);
        stage1.show();
    }
}
