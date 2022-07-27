package client_application;

import client_package.Client;
import gamelogic.Game;
import gamelogic.Position;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import messages.*;
import gamelogic.Direction;
import server_package.Server;
import utility.Images;

import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan, Qinyi, Vivian
 *
 */
public class ClientGameBasicController {
    boolean startingSubmitActive, chooseProgrammingActive, chooseDirectionActive, chooseRegisterActive, winningSceneActive, losingSceneActive;;

    public ClientApplication clientApplication;
    boolean timerCounting;

    @FXML
    private AnchorPane winnerScene;

    @FXML
    private AnchorPane loserScene;

    @FXML
    private Button buttonSubmit;

    @FXML
    private ImageView roboImage;

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
    private Text startPosText;

    @FXML
    private Text playerName;

    @FXML
    private TextField startingCoordinates;


    @FXML
    private Text timer;

    /*public ClientGameBasicController() {
        timerlabel.setFont(javafx.scene.text.Font.font(20));

        time = new Timeline(new KeyFrame(Duration.millis(1000), e -> timelabel()));
        time.setCycleCount(Timeline.INDEFINITE);
    }

    public void timelabel() {
        tmp--;
        s = "0:" + tmp + "";
        if(tmp == 0){
           //TODO: the player will be forced to end the programming phase
        }
        timerlabel.setText(s);
    }*/



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

    public void visibilityStartingChoose(Boolean visible) {
        startPosText.setVisible(visible);
        buttonSubmit.setVisible(visible);
        startingCoordinates.setVisible(visible);
    }

    public void visibilityPlayerDetails(Boolean visible){
        playerName.setText(clientApplication.getClient().getName());
        playerName.setVisible(visible);
        roboImage.setVisible(visible);
        switch(clientApplication.getClient().getFigure()) {
            case 1 -> {
                roboImage.setImage(new Image(Images.SPIN_BOT.toString()));
            }
            case 2 -> {
                    roboImage.setImage(new Image(Images.HULK_BOT.toString()));
            }
            case 3 -> {
                roboImage.setImage(new Image(Images.ZOOM_BOT.toString()));
            }
            case 4 -> {
                roboImage.setImage(new Image(Images.TWONKY_BOT.toString()));
            }
            case 5 -> {
                roboImage.setImage(new Image(Images.HAMMER_BOT.toString()));
            }
            case 6 -> {
                roboImage.setImage(new Image(Images.SMASH_BOT.toString()));
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

    /**
     * the following 2 methods start showing the winner and loser scene after a player won the game
     *
     * @author Sarp Cagin Erdogan
     */

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

    /**
     * the following 4 methods handle reboot directions of a robot
     *
     * @param event
     * @author Vivian Kafadar
     */
    @FXML
    void rebootDown(MouseEvent event) {
        setRebootDirection(Direction.BOTTOM);
        stopChoosingDirection();
    }

    @FXML
    void rebootLeft(MouseEvent event) {
        setRebootDirection(Direction.LEFT);
        stopChoosingDirection();
    }

    @FXML
    void rebootRight(MouseEvent event) {
        setRebootDirection(Direction.RIGHT);
        stopChoosingDirection();
    }

    @FXML
    void rebootUp(MouseEvent event) {
        setRebootDirection(Direction.TOP);
        stopChoosingDirection();
    }

    /**
     * starts showing direction choose field for reboots
     *
     * @author Vivian Kafadar
     */

    public void startChoosingDirection(){
        chooseDirectionActive=true;
        rebootWindow.setDisable(false);
        rebootWindow.setVisible(true);
    }

    /**
     * ends showing direction choose field for reboots
     *
     * @author Vivian Kafadar
     */

    public void stopChoosingDirection(){
        chooseDirectionActive=false;
        rebootWindow.setDisable(true);
        rebootWindow.setVisible(false);
    }

    public void setRebootDirection(Direction direction){
        clientApplication.basicClient.sendSelf(new MessageRebootDirection(direction.toString()));
    }

    /**
     * starts 30 seconds timer
     *
     * @throws InterruptedException
     * @author Isabel Muhm
     */

    public void startTimer() throws InterruptedException {
        timerCounting = true;
            for (int i = 30; i > 0 && timerCounting; i--) {
                if(i >= 10) {
                    timer.setText("0:" + i);
                }
                else if(i<10){
                    timer.setText("0:0" + i);
                }
                TimeUnit.SECONDS.sleep(1);
            }
    }

    /**
     * resets 30 seconds timer to 0:00 and ends startTimer method
     *
     * @throws InterruptedException
     * @author Isabel Muhm
     */
    public void endTimer() throws InterruptedException {
        timerCounting = false;
        TimeUnit.SECONDS.sleep(1);
        timer.setText("0:00");
    }
}


