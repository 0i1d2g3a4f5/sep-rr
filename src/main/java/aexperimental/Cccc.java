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
/**
 * @author Sarp Cagin Erdogan
 * @author Qinyi
 */
public class Cccc extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene;
        ScrollPane scrollPane = new ScrollPane();
        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        ArrayList<Card> cardArrayList = new ArrayList<>();
        CardFactory cardFactory = new CardFactory();
        cardArrayList.add(cardFactory.createCard(CardName.LEFT_TURN));
        cardArrayList.add(cardFactory.createCard(CardName.RIGHT_TURN));
        cardArrayList.add(cardFactory.createCard(CardName.LEFT_TURN));
        GridPane gridPane = javaFXGridHandler.gridPaneFromCards(cardArrayList);
        scrollPane.setContent(gridPane);
        scene = new Scene(scrollPane, 1280, 720);
        stage.setScene(scene);
        stage.show();

    }
}
