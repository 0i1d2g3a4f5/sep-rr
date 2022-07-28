package aexperimental;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.client_gamelogic.map.GameBoard;
import client_package.client_gamelogic.map.ModelLoader;
import gamelogic.Position;
import gamelogic.cards.CardName;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sarpLovesJavaFX.JavaFXGridHandler;
import utility.Images;

import java.util.ArrayList;

public class Aaa extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        for(int x = 0; x<5; x++){
            for(int y = 0; y<5; y++){
                StackPane stackPane = new StackPane(Images.BACKGROUND.toImageView());
                gridPane.add(stackPane, x, y);
            }
        }

        StackPane stackPane = (StackPane) gridPane.getChildren().get(7);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(Images.ZOOM_BOT.toImageView());

        gridPane.setMaxSize(0, 0);
        gridPane.setMinSize(0, 0);
        gridPane.setScaleX(0.3);
        gridPane.setScaleY(0.3);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();


    }
}