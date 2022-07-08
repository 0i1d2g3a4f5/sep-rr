package client_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
    private ScrollPane scrollPaneGameBoard;

    @FXML
    private StackPane stackTempProgramming;


    @FXML
    void startButton(ActionEvent event) {

    }
    public void updateProgrammingCards(GridPane gridPane){
        scrollAvailableProgramming.setContent(gridPane);
    }
    public void updateOtherRegisters(GridPane gridPane){
        scrollOtherRegisters.setContent(gridPane);
    }

    public void updateOwnRegister(GridPane gridPane){
        stackTempProgramming.getChildren().add(gridPane);
    }



    public void updateGameBoard(GridPane gridPane){
        scrollPaneGameBoard.setContent(gridPane);
    }

    // Set AnchorPane visible at start of programming phase
        // if progPhase (true) then ...
    // extract info from startingCoordinates and save
    // display info in handCardsList
    // have click of setCardButton add it to progCardsList
    // confirm whole list and send to game when clicking confirmCardsButton

    /*@FXML
    AnchorPane controlCenter() {

    }*/

}
