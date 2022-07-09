package aexperimental;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import gamelogic.cards.CardName;
import javafx.fxml.FXML;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sarpLovesJavaFX.JavaFXGridHandler;

import java.io.IOException;
import java.util.ArrayList;

public class TestController {
    Test test;
    GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    public void init(){

        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        CardFactory cardFactory = new CardFactory();
        ArrayList<Card> cardArrayList = new ArrayList<>();
        try {
            cardArrayList.add(cardFactory.createCard(CardName.LEFT_TURN));
            cardArrayList.add(cardFactory.createCard(CardName.RIGHT_TURN));
            cardArrayList.add(cardFactory.createCard(CardName.U_TURN));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gridPane = javaFXGridHandler.gridPaneFromCards(cardArrayList, true);
        scrollPane.setContent(gridPane);
    }
    @FXML
    public void getSelectedAvailableCard(MouseEvent mouseEvent) {
        Node clicked = mouseEvent.getPickResult().getIntersectedNode();
        boolean inside = false;

        while (clicked != null && clicked.getParent() !=null && !clicked.getParent().equals(gridPane)) {
            System.out.println("Current = " + clicked.toString() + " from class " + clicked.getClass().toString() + " with parent " + clicked.getParent());
            clicked = clicked.getParent();
            if(clicked.getParent() != null && clicked.getParent().equals(gridPane)){
                inside=true;
            }
        }
        if(inside) {
            int x = GridPane.getColumnIndex(clicked);
            int y = GridPane.getRowIndex(clicked);
            System.out.println(x + " " + y);
        }

    }
}
