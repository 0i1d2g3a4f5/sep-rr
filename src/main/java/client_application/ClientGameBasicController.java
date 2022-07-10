package client_application;

import gamelogic.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import newmessages.MessageSelectedCard;
import newmessages.MessageSetStartingPoint;

/**
 * @author Sarp Cagin Erdogan
 */
public class ClientGameBasicController {
    boolean startingSubmitActive = true, chooseProgrammingActive = false;

    public ClientApplication clientApplication;
    int currentChosen;

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
        stackOwnProgramming.getChildren().clear();
        stackOwnProgramming.getChildren().add(gridPane);
    }
    public void updateOtherRegisters(GridPane gridPane){
        scrollOtherRegisters.setContent(gridPane);
    }

    public void updateHandCards(GridPane gridPane){
        scrollAvailableProgramming.setContent(gridPane);
    }



    public void updateGameBoard(GridPane gridPane){
        scrollPaneGameBoard.setContent(gridPane);
    }
    public void sendStartPoint(Position position){
        clientApplication.basicClient.sendSelf(new MessageSetStartingPoint(position));

    }
    public void resetSubmitStartingPoint(){
        startingCoordinates.setText("");
        startingSubmitActive=true;
    }
    public void activateCardSelection(){
        chooseProgrammingActive=true;
    }

    public void selectCard(){
        GridPane gridPane = (GridPane) stackOwnProgramming.getChildren().get(0);
        clientApplication.getClient().getPlayer().selectCard(currentChosen, gridPane.getColumnCount());
        chooseProgrammingActive=true;
    }

    @FXML
    public void mouseClicked(MouseEvent mouseEvent) {
        if(chooseProgrammingActive){
            chooseProgrammingActive=false;
            Node clicked = mouseEvent.getPickResult().getIntersectedNode();
            System.out.println("CLICKED NODE IS: " + clicked.toString());
            boolean inside = true;
            Node requestedParent = scrollAvailableProgramming.getContent();

            while (clicked != null && clicked.getParent() != null && !clicked.getParent().equals(requestedParent)) {
                inside=false;
                //System.out.println("Current = " + clicked.toString() + " from class " + clicked.getClass().toString() + " with parent " + clicked.getParent());
                clicked = clicked.getParent();
                if (clicked.getParent() != null && clicked.getParent().equals(requestedParent)) {
                    inside = true;
                    break;
                }
            }

            if (inside) {

                int x = GridPane.getColumnIndex(clicked);
                currentChosen=x;
                //System.out.println("Index: " + currentChosen);
                GridPane own = (GridPane) stackOwnProgramming.getChildren().get(0);
                System.out.println("Chosen card is \"" + clientApplication.getClient().getPlayer().getHandCards().get(currentChosen).getCardName() + "\" for register \"" + (own.getColumnCount()) + "\"");
                clientApplication.getClient().sendSelf(new MessageSelectedCard(clientApplication.getClient().getPlayer().getHandCards().get(currentChosen).getCardName().toString(), own.getColumnCount() ));
            }
            else{
                chooseProgrammingActive=true;
            }
        }

    }
    public void init(){
        stackOwnProgramming.getChildren().add(new GridPane());
        scrollPaneGameBoard.setContent(new GridPane());
        scrollAvailableProgramming.setContent(new GridPane());
        scrollOtherRegisters.setContent(new GridPane());
    }

}
