package sarpLovesJavaFX;


import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.Checkpoint;
import client_package.client_gamelogic.game_elements.ConveyorBelt;
import client_package.client_gamelogic.game_elements.GameElement;
import client_package.client_gamelogic.game_elements.Gear;
import client_package.client_gamelogic.game_elements.robot.Robot;
import client_package.client_gamelogic.map.GameBoard;

import gamelogic.Color;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import client_package.client_gamelogic.*;
import client_package.client_gamelogic.map.*;
import utility.Images;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Sarp Cagin Erdogan
 * @author Qinyi
 */

public class JavaFXGridHandler {
    void rotate(ImageView imageView,int direction){
        imageView.setRotate(imageView.getRotate()+direction*90);
    }

    /**
     * @author Mark Ringer, Sarp Cagin Erdogan
     * @param imageView
     * @param direction
     */
    void rotateAnimated(ImageView imageView,int direction){
        for (int i = 0; i < 90; i++) {
            imageView.setRotate(imageView.getRotate()+direction);
            try {
                wait(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateAll(){

    }

    public GridPane gridPaneFromCards(ArrayList<Card> cardList, int type){
        try {
            return gridPaneFromCardList(cardList, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public ListView<String> listViewFromCards(ArrayList<Card> cardList) {
        try {
            return constructCardsList(cardList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    */

    public GridPane gridPaneFromGameBoard(GameBoard gameBoard){
        GridPane temp = null;
        try {
            temp = updateGameBoard(gameBoard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        temp.setScaleX(0.2);
        temp.setScaleY(0.2);
        return temp;
    }

    private void addToPane(StackPane stackPane,ImageView imageView, int type){

        switch (type){
            case 1 -> {
                imageView.setFitWidth(64);
                imageView.setFitHeight(98);
                break;
            }
            case 2 -> {
                imageView.setFitWidth(124);
                imageView.setFitHeight(198);
                break;
            }
            case 3 -> {
                imageView.setFitWidth(90);
                imageView.setFitHeight(158);
                break;
            }
            default -> {
                imageView.setFitWidth(512);
                imageView.setFitHeight(512);
                break;

            }
        }
        stackPane.getChildren().add(imageView);
        stackPane.setAlignment(imageView, Pos.CENTER);
    }
    public GridPane gridPaneFromBooleanList(ArrayList<Boolean> boolList, int type) throws IOException {
        GridPane gridPane = new GridPane();
        for(int i=0; i<boolList.size(); i++){
            StackPane stackPane = new StackPane();
            if(boolList.get(i)){
                addToPane(stackPane, Images.YESCARD.toImageView(), 3);
            }
            else{
                addToPane(stackPane, Images.NOCARD.toImageView(),  3);
            }
            gridPane.add(stackPane, i, 0);
        }
        return gridPane;
    }
    private GridPane gridPaneFromCardList(ArrayList<Card> cardList, int type) throws IOException {
        GridPane gridPane = new GridPane();
        //Client.clientLogger.debug("CARDLISTSIZE: " + cardList.size());
        // get card list length, add image in gridpanes at needed locations
        for(int i=0; i<cardList.size(); i++){
                if (cardList.get(i) != null) {
                    StackPane stackPane = new StackPane();
                    switch (cardList.get(i).getCardName()) {
                        // programming cards
                        case AGAIN -> addToPane(stackPane, Images.AGAIN_CARD.toImageView(), type);
                        case BACK_UP -> addToPane(stackPane, Images.BACK_UP_CARD.toImageView(), type);
                        case TURNLEFT -> addToPane(stackPane, Images.TURN_LEFT_CARD.toImageView(), type);
                        case MOVEI -> addToPane(stackPane, Images.MOVE_1_CARD.toImageView(), type);
                        case MOVEIII -> addToPane(stackPane, Images.MOVE_3_CARD.toImageView(), type);
                        case MOVEII -> addToPane(stackPane, Images.MOVE_2_CARD.toImageView(), type);
                        case POWER_UP -> addToPane(stackPane, Images.POWER_UP_CARD.toImageView(), type);
                        case TURNRIGHT -> addToPane(stackPane, Images.TURN_RIGHT_CARD.toImageView(), type);
                        case UTURN -> addToPane(stackPane, Images.U_TURN_CARD.toImageView(), type);


                        // special programming cards
                        case ENERGY_ROUTINE -> addToPane(stackPane, Images.ENERGY_ROUTINE_CARD.toImageView(), type);
                        case REPEAT_ROUTINE -> addToPane(stackPane, Images.REPEAT_ROUTINE_CARD.toImageView(), type);
                        case SANDBOX_ROUTINE -> addToPane(stackPane, Images.SANDBOX_ROUTINE_CARD.toImageView(), type);
                        case SPAM_FOLDER -> addToPane(stackPane, Images.SPAM_FOLDER_CARD.toImageView(), type);
                        case WEASEL_ROUTINE -> addToPane(stackPane, Images.WEASEL_ROUTINE_CARD.toImageView(), type);


                        // damage cards
                        case SPAM -> addToPane(stackPane, Images.SPAM_CARD.toImageView(), type);
                        case TROJAN_HORSE -> addToPane(stackPane, Images.TROJAN_HORSE_CARD.toImageView(), type);
                        case VIRUS -> addToPane(stackPane, Images.VIRUS_CARD.toImageView(), type);
                        case WORM -> addToPane(stackPane, Images.WORM_CARD.toImageView(), type);
                    }
                    gridPane.add(stackPane, i, 0);
                } else {
                    StackPane stackPane = new StackPane();
                    addToPane(stackPane, Images.NOCARD.toImageView(), type);
                    gridPane.add(stackPane, i, 0);
                }
            }

        return gridPane;
        }

    /* public Scene constructHandCards() throws IOException {
        ArrayList<Card> getHandCards = ThisCPlayer.getHandCards();

        // return handcards;
    } */

    /**
     * @author Mark Ringer, Sarp Cagin Erdogan
     * constructs the Map as JavaFX content
     * @return
     * @throws IOException
     */

    private GridPane updateGameBoard(GameBoard gameBoard) throws IOException {
        Client.clientLogger.info("Updated game board");
        Client.clientLogger.info("Robot Information:");
        for (CPlayer player:Game.getInstance().getPlayerList()) {
            if(player.getRobot()!=null){
                Client.clientLogger.debug("Player = "+player.getClientID()+"Robot="+player.getRobot().figure+ " Position="+player.getRobot().getPosition());
            }
        }
        GridPane input = new GridPane();
        input.setMaxSize(0,0);
        input.setMinSize(0,0);
        for(int y=0; y<Game.getInstance().getMap().getDimensionY(); y++){
            for(int x=0; x<gameBoard.getDimensionX(); x++){
                GameField temp = gameBoard.getGameField(y, x);
                StackPane stackPane = new StackPane();
                ImageView imageView= new ImageView(new Image("TBDtile.png"));
                stackPane.getChildren().add(imageView);
                stackPane.setAlignment(imageView, Pos.CENTER);

                for(int i=0; i<temp.getElements().size(); i++){
                    GameElement gameElement = temp.getElements().get(i);
                    switch (gameElement.getType()){
                        case LASER:
                            caseLaser(stackPane, gameElement);
                            break;
                        case CHECKPOINT:
                            caseCheckpoint(stackPane, (Checkpoint) gameElement);
                            break;
                        case CONVEYORBELT:
                            caseConveyorBelt(stackPane, gameElement);
                            break;

                        case ENERGYSPACE:

                            caseEnergySpace(stackPane);
                            break;
                        case GEAR:
                            caseGear(stackPane, (Gear) gameElement);
                            break;

                        case PUSHPANEL:
                            try {
                                casePushPanel(stackPane, gameElement);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case PIT:
                            casePit(stackPane);
                            break;
                        case STARTPOINT:
                            caseStartPoint(stackPane);

                            break;
                        case WALL:
                            caseWall(stackPane, gameElement);
                            break;
                        case ROBOT:
                            caseRobot(stackPane, (Robot) gameElement);
                            break;
                        case ANTENNA:
                            caseAntenna(stackPane, gameElement);
                            break;
                        case RESTARTPOINT:
                            caseRestartPoint(stackPane);
                            break;
                        case ENERGY_CUBE:
                            caseEnergyCube(stackPane);
                            break;
                        case EMPTY:
                            //leer
                            break;
                    }
                }
                input.add(stackPane, x, y);

            }
        }
        return input;
    }

    private void caseEnergyCube(StackPane stackPane) {
        ImageView imageView11 = new ImageView(new Image("/checkdisabled.png"));
        stackPane.getChildren().add(imageView11);
        stackPane.setAlignment(imageView11, Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param gameBoard
     * @return
     */


    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private static void caseRestartPoint(StackPane stackPane) {
        ImageView imageView10 = new ImageView(new Image("/Reboot.png"));
        stackPane.getChildren().add(imageView10);
        stackPane.setAlignment(imageView10, Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private  void caseAntenna(StackPane stackPane, GameElement gameElement) {
        switch (gameElement.orientations.get(0)){
            case TOP -> {
                ImageView imageView9 = Images.ANTENNA_NORTH_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case BOTTOM -> {
                ImageView imageView9 = Images.ANTENNA_SOUTH_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case LEFT -> {
                ImageView imageView9 = Images.ANTENNA_WEST_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case RIGHT -> {
                ImageView imageView9 = Images.ANTENNA_EAST_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
        }
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    public void caseRobot(StackPane stackPane, Robot gameElement) {
        Robot robot = gameElement;
        switch (robot.getFigure()){
            case 1 -> {
                ImageView imageView8 = Images.SPIN_BOT.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 2 -> {
                ImageView imageView8 = Images.HULK_BOT.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 3 -> {
                ImageView imageView8 = Images.HAMMER_BOT.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 4 -> {
                ImageView imageView8 = Images.TWONKY_BOT.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 5 -> {
                ImageView imageView8 = Images.HAMMER_BOT.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 6-> {
                ImageView imageView8 = Images.SMASH_BOT.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }

        }
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private void caseWall(StackPane stackPane, GameElement gameElement) {

        switch (gameElement.orientations.get(0)){
            case TOP ->{
                ImageView imageView7 = Images.WALL_TOP_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case BOTTOM -> {
                ImageView imageView7 = Images.WALL_BOTTOM_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case LEFT -> {
                ImageView imageView7 = Images.WALL_LEFT_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case RIGHT -> {
                ImageView imageView7 = Images.WALL_RIGHT_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }


        }
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private void caseStartPoint(StackPane stackPane) {
        ImageView imageView6 = Images.STARTING_POINT_ELEMENT.toImageView();
        stackPane.getChildren().add(imageView6);
        stackPane.setAlignment(imageView6,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private void casePit(StackPane stackPane) {
        ImageView imageView5 = Images.PIT_ELEMENT.toImageView();
        stackPane.getChildren().add(imageView5);
        stackPane.setAlignment(imageView5,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private void casePushPanel(StackPane stackPane, GameElement gameElement) throws IOException {
        switch(Game.getInstance().getActiveRegister()){
            case 1,3,5 -> {
                switch (gameElement.orientations.get(0)){
                    case TOP -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_TOP_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case BOTTOM -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_BOTTOM_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case RIGHT -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_RIGHT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case LEFT -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_LEFT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                }
            }
            case 2,4 ->{
                switch (gameElement.orientations.get(0)){
                    case TOP -> {
                        ImageView imageView4 = Images.PUSH_PANEL_24_TOP_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case BOTTOM -> {
                        ImageView imageView4 = Images.PUSH_PANEL_24_BOTTOM_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case RIGHT -> {
                        ImageView imageView4 = Images.PUSH_PANEL_24_RIGHT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case LEFT -> {
                        ImageView imageView4 = Images.PUSH_PANEL_24_LEFT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                }
            }
        }

        return;
    }
    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private void caseGear(StackPane stackPane, Gear gameElement) {
        Gear gear = gameElement;
        switch (gear.getGearDirection()){
            case CLOCKWISE -> {
                ImageView imageView3 =Images.GEAR_CLOCKWISE_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView3);
                stackPane.setAlignment(imageView3,Pos.CENTER);
            }
            case COUNTERCLOCKWISE -> {
                ImageView imageView3 = Images.GEAR_COUNTERCLOCKWISE_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView3);
                stackPane.setAlignment(imageView3,Pos.CENTER);
            }
        }
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private void caseEnergySpace(StackPane stackPane) {
        ImageView imageView2 = Images.ENERGY_SPACE_GREEN_ELEMENT.toImageView();
        ImageView imageview = Images.ENERGY_CUBE.toImageView();
        stackPane.getChildren().addAll(imageView2,imageview);
        stackPane.setAlignment(imageView2,Pos.CENTER);
        stackPane.setAlignment(imageview,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private void caseConveyorBelt(StackPane stackPane, GameElement gameElement) {
        ConveyorBelt conveyorBelt = (ConveyorBelt) gameElement;
        if(conveyorBelt.getColor().equals(Color.BLUE)){
            if (gameElement.orientations.size() == 1) {
                throw new IllegalArgumentException("Fucking idiot, just one conveyorbelt orientation");
            } else if (gameElement.orientations.size() == 2) {
                switch (gameElement.orientations.get(0)) {
                    case TOP -> {
                        switch (gameElement.orientations.get(1)) {
                            case TOP -> {
                            }
                            case BOTTOM -> {
                                ImageView imageView1 = Images.BELT_BLUE_UP_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case RIGHT -> {
                                ImageView imageView1 = Images.BELT_BLUE_RIGHT_TO_UP.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case LEFT -> {
                                ImageView imageView1 = Images.BELT_BLUE_LEFT_TO_UP_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                        }
                    }
                    case BOTTOM -> {
                        switch (gameElement.orientations.get(1)) {

                            case TOP -> {
                                ImageView imageView1 = Images.BELT_BLUE_DOWN_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case BOTTOM -> {
                            }
                            case RIGHT -> {
                                ImageView imageView1 = Images.BELT_BLUE_RIGHT_TO_BOTTOM.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case LEFT -> {
                                ImageView imageView1 = Images.BELT_BLUE_LEFT_TO_BOTTOM.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                        }
                    }
                    case RIGHT -> {
                        switch (gameElement.orientations.get(1)) {

                            case TOP -> {
                                ImageView imageView1 = Images.BELT_BLUE_TOP_TO_RIGHT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case BOTTOM -> {
                                ImageView imageView1 = Images.BELT_BLUE_BOTTOM_TO_RIGHT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case RIGHT -> {
                            }
                            case LEFT -> {
                                ImageView imageView1 = Images.BELT_BLUE_RIGHT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                        }
                    }
                    case LEFT -> {
                        switch (gameElement.orientations.get(1)) {
                            case TOP -> {
                                ImageView imageView1 = Images.BELT_BLUE_TOP_TO_LEFT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case BOTTOM -> {
                                ImageView imageView1 = Images.BELT_BLUE_BOTTOM_TO_LEFT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case RIGHT -> {
                                ImageView imageView1 = Images.BELT_BLUE_LEFT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case LEFT -> {

                            }
                        }
                    }
                }
            }
            else if(gameElement.orientations.size() == 3) {
                switch(gameElement.orientations.get(0)){
                    case TOP -> {
                        switch(gameElement.orientations.get(1)){
                            case BOTTOM -> {
                                switch(gameElement.orientations.get(2)){
                                    case RIGHT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_RIGHT_TO_TOP_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                    case LEFT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_LEFT_TO_TOP_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case RIGHT -> {
                                switch(gameElement.orientations.get(2)){
                                    case BOTTOM -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_RIGHT_TO_TOP_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case LEFT -> {
                                switch(gameElement.orientations.get(2)){
                                    case BOTTOM -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_LEFT_TO_TOP_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                        }
                    }
                    case BOTTOM -> {
                        switch(gameElement.orientations.get(1)){
                            case TOP -> {
                                switch(gameElement.orientations.get(2)){
                                    case RIGHT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_RIGHT_TO_BOTTOM_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                    case LEFT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_LEFT_TO_BOTTOM_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case RIGHT -> {
                                switch(gameElement.orientations.get(2)){
                                    case TOP -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_RIGHT_TO_BOTTOM_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case LEFT -> {
                                switch(gameElement.orientations.get(2)){
                                    case TOP -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_LEFT_TO_BOTTOM_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                        }
                    }
                    case RIGHT -> {
                        switch(gameElement.orientations.get(1)){
                            case TOP -> {
                                switch(gameElement.orientations.get(2)){
                                    case LEFT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_LEFT_TO_RIGHT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case BOTTOM -> {
                                switch(gameElement.orientations.get(2)){
                                    case LEFT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_LEFT_TO_RIGHT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case LEFT -> {
                                switch(gameElement.orientations.get(2)){
                                    case TOP -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_LEFT_TO_RIGHT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                    case BOTTOM -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_LEFT_TO_RIGHT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                        }
                    }
                    case LEFT -> {
                        switch(gameElement.orientations.get(1)){
                            case TOP -> {
                                switch(gameElement.orientations.get(2)){
                                    case RIGHT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_RIGHT_TO_LEFT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case BOTTOM -> {
                                switch(gameElement.orientations.get(2)){
                                    case RIGHT -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_RIGHT_TO_LEFT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                            case RIGHT -> {
                                switch(gameElement.orientations.get(2)){
                                    case TOP -> {
                                        ImageView imageView1 = Images.BELT_BLUE_TOP_RIGHT_TO_LEFT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                    case BOTTOM -> {
                                        ImageView imageView1 = Images.BELT_BLUE_BOTTOM_RIGHT_TO_LEFT_ELEMENT.toImageView();
                                        stackPane.getChildren().add(imageView1);
                                        stackPane.setAlignment(imageView1, Pos.CENTER);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
            /*case BLUE -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        if (gameElement.orientations.get(1) != null) {
                            switch (gameElement.orientations.get(1)){

                                case NORTH-> {
                                }
                                case SOUTH -> {
                                    ImageView imageView1= Images.BELT_BLUE_UP_ELEMENT.toImageView();
                                    stackPane.getChildren().add(imageView1);
                                    stackPane.setAlignment(imageView1, Pos.CENTER);
                                }
                                case EAST -> {
                                    ImageView imageView1= Images.BELT_BLUE_UP_TO_LEFT_ELEMENT.imageView;
                                    stackPane.getChildren().add(imageView1);
                                    stackPane.setAlignment(imageView1, Pos.CENTER);
                                }
                                case WEST -> {
                                    ImageView imageView1= Images.BELT_BLUE_UP_TO_RIGHT_ELEMENT.imageView;
                                    stackPane.getChildren().add(imageView1);
                                    stackPane.setAlignment(imageView1, Pos.CENTER);
                                }
                            }
                        }

                    }
                    case SOUTH -> {
                        if (gameElement.orientations.get(1) != null) {
                            switch (gameElement.orientations.get(1)){

                                case NORTH-> {

                                    ImageView imageView1= Images.BELT_BLUE_DOWN_ELEMENT.toImageView();
                                    stackPane.getChildren().add(imageView1);
                                    stackPane.setAlignment(imageView1, Pos.CENTER);
                                }
                                case SOUTH -> {
                                }
                                case EAST -> {
                                    ImageView imageView1= Images.BELT_BLUE_UP_TO_LEFT_ELEMENT.imageView;
                                    stackPane.getChildren().add(imageView1);
                                    stackPane.setAlignment(imageView1, Pos.CENTER);
                                }
                                case WEST -> {
                                    ImageView imageView1= Images.BELT_BLUE_UP_TO_RIGHT_ELEMENT.imageView;
                                    stackPane.getChildren().add(imageView1);
                                    stackPane.setAlignment(imageView1, Pos.CENTER);
                                }
                            }
                        }
                    }
                    case EAST-> {
                        ImageView imageView1= new ImageView(new Image("/BeltBlueRight.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= new ImageView(new Image("/BeltBlueLeft.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                }
            }
            case GREEN -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView1= Images.BELT_GREEN_UP_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView1= Images.BELT_GREEN_DOWN_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case EAST-> {
                        ImageView imageView1 = Images.BELT_GREEN_RIGHT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= Images.BELT_GREEN_LEFT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                }
            }*/

    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private void caseCheckpoint(StackPane stackPane, Checkpoint gameElement) {
        Checkpoint checkpoint = gameElement;
        switch (checkpoint.getCount()){
            case 1,0 -> {
                ImageView imageViewcheck = new ImageView(new Image("/CheckPoint1.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 2 ->{
                ImageView imageViewcheck = new ImageView(new Image("/CheckPoint2.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 3->{
                ImageView imageViewcheck = new ImageView(new Image("/CheckPoint3.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 4->{
                ImageView imageViewcheck = new ImageView(new Image("/CheckPoint4.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
        }
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private void caseLaser(StackPane stackPane, GameElement gameElement) {
        switch(gameElement.orientations.get(0)) {
            case TOP -> {
                ImageView imageView0 = new ImageView(new Image("/LaserUp.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case BOTTOM -> {
                ImageView imageView0 = new ImageView(new Image("/LaserDown.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0,Pos.CENTER);
            }
            case RIGHT -> {
                ImageView imageView0 = new ImageView(new Image("/LaserLeft.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case LEFT -> {
                ImageView imageView0 = new ImageView(new Image("/LaserRight.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
        }
        return;
    }
}
