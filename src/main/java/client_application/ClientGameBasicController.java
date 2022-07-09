package client_application;

import gamelogic.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import newmessages.MessageSetStartingPoint;

/**
 * @author Sarp Cagin Erdogan
 */
public class ClientGameBasicController {
    boolean startingSubmitActive = true;

    public ClientApplication clientApplication;
    public GridPane gameBoard;
    public GridPane ownRegister;
    public GridPane availableCards;
    public GridPane otherRegisters;

    @FXML
    private ScrollPane scrollAvailableProgramming;

    @FXML
    private ScrollPane scrollOtherRegisters;

    @FXML
    private ScrollPane scrollPaneGameBoard;

    @FXML
    private StackPane stackOwnProgramming;


    @FXML
    void startButton(ActionEvent event) {

    }
    @FXML
    private TextField startingCoordinates;

    @FXML
    void submitButton(ActionEvent event) {

        if(startingCoordinates.getText().trim()!="" && startingSubmitActive) {
            String[] singleCoordinate ={};
            String toCheck =startingCoordinates.getText().trim();
            boolean formatGood = false;
            for(int i = 0; i<toCheck.length(); i++){
                if(toCheck.charAt(i)=='/'){
                    formatGood=true;
                }
            }
            if(!formatGood){
                startingCoordinates.setText("GIVE PROPER COORDINATE BVITCH ");
            }
            else {
                singleCoordinate = toCheck.split("/");

                if (singleCoordinate.length != 2) {
                    startingCoordinates.setText("GIVE PROPER COORDINATE BVITCH ");
                } else {
                    boolean proper = false;
                    int x = -1, y = -1;
                    try {
                        x = Integer.parseInt(singleCoordinate[0]);
                        y = Integer.parseInt(singleCoordinate[1]);
                        proper=true;
                    } catch (NumberFormatException e) {
                        startingCoordinates.setText("write number idiot");
                    }
                    if(proper) {
                        Position pos = new Position(y, x);

                        startingSubmitActive=false;
                        sendStartPoint(pos);
                    }

                }
            }


        }


    }

    public void updateProgrammingCards(GridPane gridPane){
        availableCards=gridPane;
        scrollAvailableProgramming.setContent(availableCards);
    }
    public void updateOtherRegisters(GridPane gridPane){
        otherRegisters=gridPane;
        scrollOtherRegisters.setContent(otherRegisters);
    }

    public void updateHandCards(GridPane gridPane){
        ownRegister=gridPane;
        stackOwnProgramming.getChildren().clear();
        stackOwnProgramming.getChildren().add(ownRegister);
    }



    public void updateGameBoard(GridPane gridPane){
        gameBoard = gridPane;
        scrollPaneGameBoard.setContent(gameBoard);
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

    public void sendStartPoint(Position position){
        clientApplication.basicClient.sendSelf(new MessageSetStartingPoint(position));

    }
    public void resetSubmitStartingPoint(){
        startingCoordinates.setText("");
        startingSubmitActive=true;
    }
}
