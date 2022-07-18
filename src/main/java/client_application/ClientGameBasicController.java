package client_application;

import client_package.Client;
import gamelogic.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import newmessages.*;
import gamelogic.Direction;

/**
 * @author Sarp Cagin Erdogan, Qinyi, Vivian
 *
 */
public class ClientGameBasicController {
    boolean startingSubmitActive, chooseProgrammingActive, chooseDirectionActive, chooseRegisterActive, winningSceneActive, losingSceneActive;;

    public ClientApplication clientApplication;

    @FXML
    private AnchorPane winnerScene;

    @FXML
    private AnchorPane loserScene;

    @FXML
    private ScrollPane scrollAvailableProgramming;

    @FXML
    private ScrollPane scrollOtherRegisters;

    @FXML
    private ScrollPane scrollPaneGameBoard;

    @FXML
    private StackPane stackOwnProgramming;

    @FXML
    private AnchorPane rebootWindow;

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
                startingCoordinates.setText("Please submit coordinates in the proper format");
            }
            else {
                singleCoordinate = toCheck.split("/");

                if (singleCoordinate.length != 2) {
                    startingCoordinates.setText("Please submit coordinates in the proper format");
                } else {
                    boolean proper = false;
                    int x = -1, y = -1;
                    try {
                        x = Integer.parseInt(singleCoordinate[0]);
                        y = Integer.parseInt(singleCoordinate[1]);
                        proper=true;
                    } catch (NumberFormatException e) {
                        startingCoordinates.setText("Please submit the starting coordinates");
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
    public void setStartingText(String s){
        startingCoordinates.setText(s);
    }

    public void updateProgrammingCards(GridPane gridPane){
        gridPane.setGridLinesVisible(true);
        stackOwnProgramming.getChildren().clear();
        stackOwnProgramming.getChildren().add(gridPane);
    }
    public void updateOtherRegisters(GridPane gridPane){
        gridPane.setGridLinesVisible(true);
        scrollOtherRegisters.setContent(gridPane);
    }

    public void updateHandCards(GridPane gridPane){
        gridPane.setGridLinesVisible(true);
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
    public void activateProgrammingSelection(boolean bo){
        chooseProgrammingActive=bo;
    }
    public void activateStartingPoint(boolean bo){
        startingSubmitActive=bo;
    }
    public void activateRegisterSelection(boolean bo){
        chooseRegisterActive=bo;
    }
    public void resetRegisterCards() {
    stackOwnProgramming.getChildren().clear();
    }

    public void selectCard(){
        clientApplication.getClient().getPlayer().placeSelectedToRegisterOwn();
        chooseProgrammingActive=true;
    }

    @FXML
    public void mouseClicked(MouseEvent mouseEvent) {
        if(chooseProgrammingActive){
            chooseProgrammingActive=false;
            Node clicked = mouseEvent.getPickResult().getIntersectedNode();
            Client.clientLogger.info("CLICKED NODE IS: " + clicked.toString());
            boolean inside = true;
            Node requestedParent = scrollAvailableProgramming.getContent();

            while (clicked != null && clicked.getParent() != null && !clicked.getParent().equals(requestedParent)) {
                inside=false;
                clicked = clicked.getParent();
                if (clicked.getParent() != null && clicked.getParent().equals(requestedParent)) {
                    inside = true;
                    break;
                }
            }

            if (GridPane.getColumnIndex(clicked)!=null && inside) {
                int x = GridPane.getColumnIndex(clicked);
                if(clientApplication.getClient().getPlayer().getAvailableCardsOwn().get(x)==null){
                    chooseProgrammingActive=true;
                }
                else{
                    int emptyRegister = clientApplication.getClient().getPlayer().selectCardOwn(x);
                    clientApplication.getClient().sendSelf(new MessageSelectedCard(clientApplication.getClient().getPlayer().getOwnSelectedCard().getValue().toString(), emptyRegister));
                }
            }
            else{
                chooseProgrammingActive=true;
            }
        }
        if(chooseRegisterActive){
            chooseRegisterActive=false;
            Node requestedParent = stackOwnProgramming.getChildren().get(0);
            Node clicked = mouseEvent.getPickResult().getIntersectedNode();
            if(clicked.getParent()!=null){
                if(clicked.getParent().getParent()!=null){
                }
            }

            boolean inside = true;


            while (clicked != null && clicked.getParent() != null && !clicked.getParent().equals(requestedParent)) {
                inside=false;
                clicked = clicked.getParent();
                if (clicked.getParent() != null){
                }
                if (clicked.getParent() != null && clicked.getParent().equals(requestedParent)) {
                    inside = true;
                    break;
                }
            }

            if (inside) {
                int x = GridPane.getColumnIndex(clicked);
                if(clientApplication.getClient().getPlayer().getRegisterCardsOwn().get(x)==null){
                    chooseRegisterActive=true;
                }
                else{
                    clientApplication.getClient().getPlayer().selectToBeRemovedCardOwn(x);
                    clientApplication.getClient().sendSelf(new MessageSelectedCard("Null", x));
                }
            }
            else{
                chooseRegisterActive=true;
            }
        }
        

    }
    public void init(){
        stackOwnProgramming.getChildren().clear();
        stackOwnProgramming.getChildren().add(new GridPane());
        scrollPaneGameBoard.setContent(new GridPane());
        scrollAvailableProgramming.setContent(new GridPane());
        scrollOtherRegisters.setContent(new GridPane());
        startingSubmitActive=false;
        chooseProgrammingActive=false;
        chooseRegisterActive=false;
        chooseDirectionActive=false;
        winningSceneActive=false;
        losingSceneActive=false;
        winnerScene.setVisible(false);
        winnerScene.setDisable(true);
        loserScene.setVisible(false);
        winnerScene.setDisable(true);
        rebootWindow.setVisible(false);
        rebootWindow.setDisable(true);
    }

    public void triggerGameFinishedScene(){
        //TODO: Check how to get the clientID of the winner
        /*
        MessageGameFinished messageGameFinished = new MessageGameFinished(0);
        for(Player player:Game.getInstance().getPlayerList()) {
            if (messageGameFinished.clientID == player.getClient().getId()) {
                winnerScene.setVisible(true);
            } else {
                loserScene.setVisible(true);
            }
        }

         */
    }

    public void startWinnerScene(){
        winnerScene.setDisable(false);
        winnerScene.setVisible(true);
    }

    public void startLoserScene(){
        loserScene.setDisable(false);
        loserScene.setVisible(true);
    }

    public void goLobby(ActionEvent event){
        winnerScene.setDisable(true);
        winnerScene.setVisible(false);
        loserScene.setDisable(true);
        loserScene.setVisible(false);
    }


    @FXML
    void rebootDown(MouseEvent event) {
        setRebootDirection(Direction.SOUTH);
        stopChoosingDirection();
    }

    @FXML
    void rebootLeft(MouseEvent event) {
        setRebootDirection(Direction.WEST);
        stopChoosingDirection();
    }

    @FXML
    void rebootRight(MouseEvent event) {
        setRebootDirection(Direction.EAST);
        stopChoosingDirection();
    }

    @FXML
    void rebootUp(MouseEvent event) {
        setRebootDirection(Direction.NORTH);
        stopChoosingDirection();
    }

    public void startChoosingDirection(){
        chooseDirectionActive=true;
        rebootWindow.setDisable(false);
        rebootWindow.setVisible(true);
    }

    public void stopChoosingDirection(){
        chooseDirectionActive=false;
        rebootWindow.setDisable(true);
        rebootWindow.setVisible(false);
    }

    public void setRebootDirection(Direction direction){
        clientApplication.basicClient.sendSelf(new MessageRebootDirection(direction.toString()));
    }
}