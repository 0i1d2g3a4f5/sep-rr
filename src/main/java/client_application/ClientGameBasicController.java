package client_application;

import gamelogic.Game;
import gamelogic.Player;
import gamelogic.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import newmessages.MessageSetStartingPoint;

/**
 * @author Sarp Cagin Erdogan
 */
public class ClientGameBasicController {

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
        String coordinatesAsString = startingCoordinates.getText().trim();
        String[] singleCoordinate = coordinatesAsString.split("/");
        System.out.println("submitButton");
        Position pos = new Position(Integer.parseInt(singleCoordinate[1].trim()),Integer.parseInt(singleCoordinate[0].trim()));
        System.out.println("Starting Position: "+pos);
        setStartPoint(pos);


    }

    public void updateProgrammingCards(GridPane gridPane){
        availableCards=gridPane;
    }
    public void updateOtherRegisters(GridPane gridPane){
        otherRegisters=gridPane;
    }

    public void updateHandCards(GridPane gridPane){
        ownRegister=gridPane;
    }



    public void updateGameBoard(GridPane gridPane){
        gameBoard=gridPane;
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
    public void selectStartPoint(Group group){
        group.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = GridPane.getColumnIndex(group);
                int y = GridPane.getRowIndex(group);
                Position position = new Position(x,y);
                setStartPoint(position);
            }
        });
    }

    public void setStartPoint(Position position){
        for(Player player : Game.getInstance().playerList){
            /*
            if(player.getRobot().getPosition().equals(position)){
                System.out.println("This Start Point is taken. Please choose another one.");
                return;
            }

             */

        }

        //TODO Option for advanced
        clientApplication.basicClient.sendSelf(new MessageSetStartingPoint(position.getX(),position.getY()));

        System.out.println("You"+"take the Start Point" + position);
    }
    public void init(){
        gameBoard=new GridPane();
        scrollPaneGameBoard.setContent(gameBoard);
        ownRegister=new GridPane();
        stackOwnProgramming.getChildren().add(ownRegister);
        otherRegisters = new GridPane();
        scrollOtherRegisters.setContent(otherRegisters);
        availableCards=new GridPane();
        scrollAvailableProgramming.setContent(availableCards);
    }
    public void getSelectedAvailableCard(MouseEvent mouseEvent){
        Node clicked = mouseEvent.getPickResult().getIntersectedNode();
        System.out.println(clicked.getClass().toString());

    }
}
