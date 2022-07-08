package client_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ListView;;

/**
 * @author Sarp Cagin Erdogan
 */
public class ClientGameBasicController {

    public ClientApplication clientApplication;

    @FXML
    private ScrollPane scrollAvailableProgramming;

    @FXML
    private ScrollPane scrollOtherRegisters;

    @FXML
    private ScrollPane scrollChosenProgramming;

    @FXML
    private StackPane stackTempProgramming;

    /*
    @FXML
    private ListView<String> handCardsList;
    
    @FXML
    private ListView<String> progCardsList;
    */

    @FXML
    private ScrollPane scrollPaneGameBoard;

    @FXML
    void startButton(ActionEvent event) {
    }

    /* @FXML
    void confirmCards(ActionEvent event) {
    }
    
    @FXML
    void confirmCardsButton(ActionEvent event) {
    }
    */

    public void updateGameBoard(GridPane gridPane){
        scrollPaneGameBoard.setContent(gridPane);
    }

    public void updateHandCards(GridPane gridPane){
        scrollAvailableProgramming.setContent(gridPane);
    }

    public void updateProgrammingCards(GridPane gridPane) {
        scrollChosenProgramming.setContent(gridPane);
    }

    public void updateOtherRegisters(GridPane gridPane){
        scrollOtherRegisters.setContent(gridPane);
    }

    public void updateOwnRegister(GridPane gridPane){
        stackTempProgramming.getChildren().add(gridPane);
    }

    // if prog phase true show hand cards
    // have click of setCardButton add it to progCardsList
    // confirm whole list and send to game when clicking confirmCardsButton
}
