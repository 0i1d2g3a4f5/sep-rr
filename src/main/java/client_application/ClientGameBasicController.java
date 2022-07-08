package client_application;

import gamelogic.Game;
import gamelogic.Player;
import gamelogic.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

    //TODO: check if it is correct for players to select a start point
    public void selectStartPoint(MouseEvent e){
        Group group = null;
        int x = GridPane.getColumnIndex(group);
        int y = GridPane.getRowIndex(group);
        Position position = new Position(x,y);
        setStartPoint(position);
    }

    public void setStartPoint(Position position){
        for(Player player : Game.getInstance().playerList){
            if(player.getRobot().getPosition() == position){
                System.out.println("This Start Point is taken. Please choose another one.");
            }
            else{
                player.getRobot().setPosition(position);
                System.out.println("The Client: "+player.getClient().getId()+ "takes the Start Point" + position);
            }
        }
    }



}
