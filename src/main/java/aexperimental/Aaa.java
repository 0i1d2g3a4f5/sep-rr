package aexperimental;

import client_package.client_gamelogic.map.GameBoard;
import client_package.client_gamelogic.map.ModelLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sarpLovesJavaFX.JavaFXGridHandler;

public class Aaa extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene;
        System.out.println("a");
        ScrollPane scrollPane = new ScrollPane();
        System.out.println("b");
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        ModelLoader modelLoader = new ModelLoader();
        System.out.println("c");
        GameBoard gameBoard = modelLoader.loadMap("dizzy_highway");
        System.out.println("edfsfds");
        GridPane gridPane = javaFXGridHandler.gridPaneFromGameBoard(gameBoard);
        System.out.println("d");
        scrollPane.setContent(gridPane);
        System.out.println("e");
        scene = new Scene(scrollPane, 1280, 720);
        System.out.println("f");
        stage.setScene(scene);
        System.out.println("g");
        stage.show();


    }
}
