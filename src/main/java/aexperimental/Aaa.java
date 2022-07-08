package aexperimental;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.client_gamelogic.map.GameBoard;
import client_package.client_gamelogic.map.ModelLoader;
import gamelogic.cards.CardName;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sarpLovesJavaFX.JavaFXGridHandler;

import java.util.ArrayList;

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
        scene = new Scene(scrollPane, 1440, 810);
        System.out.println("f");
        stage.setScene(scene);
        System.out.println("g");
        stage.show();



        /*Scene scene;
        ScrollPane scrollPane = new ScrollPane();
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        ArrayList<Card> cardArrayList = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();
        cardArrayList.add(cardFactory.createCard(CardName.LEFT_TURN));
        cardArrayList.add(cardFactory.createCard(CardName.LEFT_TURN));
        cardArrayList.add(cardFactory.createCard(CardName.LEFT_TURN));
        cardArrayList.add(cardFactory.createCard(CardName.LEFT_TURN));
        GridPane gridPaneNew = javaFXGridHandler.gridPaneFromCards(cardArrayList);
        scrollPane.setContent(gridPaneNew);
        scene = new Scene(scrollPane, 1280, 720);
        stage.setScene(scene);
        stage.show();*/

    }
}