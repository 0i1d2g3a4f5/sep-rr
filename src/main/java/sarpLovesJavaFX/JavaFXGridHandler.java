package sarpLovesJavaFX;


import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.*;
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
import javafx.scene.text.Text;
import utility.Images;
import utility.SearchMethods;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Sarp Cagin Erdogan, Isabel Muhm
 * @author Qinyi
 */

public class JavaFXGridHandler {
    public void applesAndBananas(GridPane desmondBind, int x, int y, GameBoard pute, int qwerty){
        GameField hurricaneCatcher = pute.getGameField(y, x);
        StackPane orangutanKebab= (StackPane) desmondBind.getChildren().get(y*desmondBind.getColumnCount() + x);
        ImageView whatDaFuk = new ImageView(new Image("TBDtile.png"));
        orangutanKebab.getChildren().add(whatDaFuk);
        orangutanKebab.setAlignment(whatDaFuk, Pos.CENTER);

        for(int i=0; i<hurricaneCatcher.getElements().size(); i++){
            GameElement gameElement = hurricaneCatcher.getElements().get(i);
            switch (gameElement.getType()){
                case LASER:
                    caseLaser(orangutanKebab, gameElement);
                    break;
                case LASERBEAM:
                    Client.clientLogger.debug("caseLaserBeam");
                    caseLaserBeam(orangutanKebab, gameElement);
                    break;
                case CHECKPOINT:
                    caseCheckpoint(orangutanKebab, (Checkpoint) gameElement);
                    break;
                case CONVEYORBELT:
                    caseConveyorBelt(orangutanKebab, gameElement);
                    break;

                case ENERGYSPACE:
                    caseEnergySpace(orangutanKebab);
                    caseEnergyCube(orangutanKebab);
                    break;

                case GEAR:
                    caseGear(orangutanKebab, (Gear) gameElement);
                    break;

                case PUSHPANEL:
                    try {
                        casePushPanel(orangutanKebab, gameElement);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case PIT:
                    casePit(orangutanKebab);
                    break;
                case STARTPOINT:
                    caseStartPoint(orangutanKebab);

                    break;
                case WALL:
                    caseWall(orangutanKebab, gameElement);
                    break;
                case ROBOT:
                    caseRobot(orangutanKebab, (Robot) gameElement, qwerty);
                    break;
                case ANTENNA:
                    caseAntenna(orangutanKebab, gameElement);
                    break;
                case RESTARTPOINT:
                    caseRestartPoint(orangutanKebab);
                    break;
                case ENERGY_CUBE:
                    caseEnergyCube(orangutanKebab);
                    break;
                case EMPTY:
                    //leer
                    break;
            }
        }
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

    public GridPane gridPaneFromGameBoard(GameBoard gameBoard, int ownFigure){
        GridPane temp = null;
        try {
            temp = updateGameBoard(gameBoard, ownFigure);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        temp.setScaleX(0.2);
        temp.setScaleY(0.2);
        return temp;
    }
    public ImageView robotPic(int figure){
        switch (figure){
            case 1 -> {
                return Images.SPIN_BOT.toImageView();
            }
            case 2 -> {
                return Images.HULK_BOT.toImageView();
            }
            case 3 -> {
                return Images.ZOOM_BOT.toImageView();
            }
            case 4 -> {
                return Images.TWONKY_BOT.toImageView();
            }
            case 5 -> {
                return Images.HAMMER_BOT.toImageView();
            }
            case 6-> {
                return Images.SMASH_BOT.toImageView();
            }
            default -> {
            }
        }
        return null;
    }

    private void addToPane(StackPane stackPane,ImageView imageView, int type){

        switch (type){
            case 1 -> {
                imageView.setFitWidth(64);
                imageView.setFitHeight(98);
                break;
            }
            case 2 -> {
                imageView.setFitWidth(115.6);
                imageView.setFitHeight(175);
                break;
            }
            case 3 -> {
                imageView.setFitWidth(65);
                imageView.setFitHeight(100);
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
    public GridPane gridPaneFromBooleanList(ArrayList<Boolean> boolList, String name, Integer figure, int type) throws IOException {
        GridPane gridPane = new GridPane();
        for(int i=0; i<boolList.size(); i++){
            StackPane stackPane = new StackPane();
            if(boolList.get(i)){
                addToPane(stackPane, Images.YESCARD.toImageView(), 3);
            }
            else{
                addToPane(stackPane, Images.NOCARD.toImageView(),  3);
            }
            Text nameText = new Text(name);
            stackPane.getChildren().add(nameText);
            stackPane.setAlignment(nameText, Pos.BOTTOM_LEFT);
            ImageView imageView = robotPic(figure);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            stackPane.getChildren().add(imageView);
            stackPane.setAlignment(imageView, Pos.BOTTOM_RIGHT);
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

    private GridPane updateGameBoard(GameBoard gameBoard, int ownFigure) throws IOException {
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

                //Client.clientLogger.debug(temp.getPosition()+" :: "+temp.getElements());

                for(int i=0; i<temp.getElements().size(); i++){
                    GameElement gameElement = temp.getElements().get(i);
                    switch (gameElement.getType()){
                        case LASER:
                            caseLaser(stackPane, gameElement);
                            break;
                        case LASERBEAM:
                            Client.clientLogger.debug("caseLaserBeam");
                            caseLaserBeam(stackPane, gameElement);
                            break;
                        case CHECKPOINT:
                            caseCheckpoint(stackPane, (Checkpoint) gameElement);
                            break;
                        case CONVEYORBELT:
                            caseConveyorBelt(stackPane, gameElement);
                            break;

                        case ENERGYSPACE:
                            caseEnergySpace(stackPane);
                            caseEnergyCube(stackPane);
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
                            caseRobot(stackPane, (Robot) gameElement, ownFigure);
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
        ImageView imageView11 = new ImageView(new Image("/EnergyCube.png"));
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
        ImageView imageView10 = Images.REBOOT_ELEMENT.toImageView();
        //new ImageView(new Image("/Reboot.png"));
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
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer, Isabel
     * @param stackPane
     * @param gameElement
     */
    public void caseRobot(StackPane stackPane, Robot gameElement, int ownFigure) {
        Robot robot = gameElement;
        if(robot.getFigure()==ownFigure){
            ImageView imageView = Images.SELF_HIGHLIGHT.toImageView();
            stackPane.getChildren().add(imageView);
            stackPane.setAlignment(imageView, Pos.CENTER);
        }
        switch (robot.getFigure()){
            case 1 -> {
                ImageView imageView8 = Images.SPIN_BOT_BEV.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 2 -> {
                ImageView imageView8 = Images.HULK_BOT_BEV.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 3 -> {
                ImageView imageView8 = Images.ZOOM_BOT_BEV.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 4 -> {
                ImageView imageView8 = Images.TWONKY_BOT_BEV.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 5 -> {
                ImageView imageView8 = Images.HAMMER_BOT_BEV.toImageView(robot.getDirectionFacing());
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 6-> {
                ImageView imageView8 = Images.SMASH_BOT_BEV.toImageView(robot.getDirectionFacing());
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
            case BOTTOM-> {
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
        Client.clientLogger.debug("push panel activated " + gameElement.getGameField().getPosition());
        PushPanel pushPanel = (PushPanel) gameElement;
        if(SearchMethods.listContainsAll(pushPanel.getActivateRegisters(), 1,3,5)){
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
        else if(SearchMethods.listContainsAll(pushPanel.getActivateRegisters(), 2,4)){
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
        else {
            Client.clientLogger.error("PushPanel without correct activateRegisters list, list is: " + pushPanel.getActivateRegisters() + "on position " + pushPanel.getGameField().getPosition());
            throw new IllegalArgumentException("PushPanel without correct activateRegisters list, list is: " + pushPanel.getActivateRegisters() + "on position " + pushPanel.getGameField().getPosition());
        }
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
        stackPane.setAlignment(imageview, Pos.CENTER);
        return;
    }

    /**
     * @author Isabel Muhm
     * @param stackPane
     * @param gameElement
     */
    private void caseConveyorBelt(StackPane stackPane, GameElement gameElement) {
        ConveyorBelt conveyorBelt = (ConveyorBelt) gameElement;
        if(conveyorBelt.getColor().equals(Color.BLUE)){
            if (gameElement.orientations.size() == 1) {
                throw new IllegalArgumentException("Blue ConveyorBelt with just one orientation");
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
        else if(conveyorBelt.getColor().equals(Color.GREEN)){
            if(gameElement.orientations.size() == 1){
                throw new IllegalArgumentException("Green ConveyorBelt with just one orientation");
            }
            else if (gameElement.orientations.size() == 2){
                switch(gameElement.orientations.get(0)){
                    case TOP -> {
                        switch(gameElement.orientations.get(1)){
                            case TOP -> {
                            }
                            case BOTTOM -> {
                                ImageView imageView1 = Images.BELT_GREEN_UP_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case RIGHT -> {
                                ImageView imageView1 = Images.BELT_GREEN_RIGHT_TO_UP_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case LEFT -> {
                                ImageView imageView1 = Images.BELT_GREEN_LEFT_TO_UP_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                        }
                    }
                    case BOTTOM -> {
                        switch(gameElement.orientations.get(1)){
                            case TOP -> {
                                ImageView imageView1 = Images.BELT_GREEN_BOTTOM_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case BOTTOM -> {
                            }
                            case RIGHT -> {
                                ImageView imageView1 = Images.BELT_GREEN_RIGHT_TO_BOTTOM_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case LEFT -> {
                                ImageView imageView1 = Images.BELT_GREEN_LEFT_TO_BOTTOM_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                        }
                    }
                    case RIGHT -> {
                        switch(gameElement.orientations.get(1)){
                            case TOP -> {
                                ImageView imageView1 = Images.BELT_GREEN_TOP_TO_RIGHT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case BOTTOM -> {
                                ImageView imageView1 = Images.BELT_GREEN_BOTTOM_TO_RIGHT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case RIGHT -> {
                            }
                            case LEFT -> {
                                ImageView imageView1 = Images.BELT_GREEN_RIGHT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                        }
                    }
                    case LEFT -> {
                        switch(gameElement.orientations.get(1)){
                            case TOP -> {
                                ImageView imageView1 = Images.BELT_GREEN_TOP_TO_LEFT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case BOTTOM -> {
                                ImageView imageView1 = Images.BELT_GREEN_BOTTOM_TO_LEFT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case RIGHT -> {
                                ImageView imageView1 = Images.BELT_GREEN_LEFT_ELEMENT.toImageView();
                                stackPane.getChildren().add(imageView1);
                                stackPane.setAlignment(imageView1, Pos.CENTER);
                            }
                            case LEFT -> {
                            }
                        }
                    }
                }
            }
        }
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
                ImageView imageViewcheck = Images.CHECKPOINT_1_ELEMENT.toImageView();
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 2 ->{
                ImageView imageViewcheck = Images.CHECKPOINT_2_ELEMENT.toImageView();
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 3->{
                ImageView imageViewcheck = Images.CHECKPOINT_3_ELEMENT.toImageView();
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 4->{
                ImageView imageViewcheck = Images.CHECKPOINT_4_ELEMENT.toImageView();
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 5 -> {
                ImageView imageViewcheck = Images.CHECKPOINT_5_ELEMENT.toImageView();
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

    private void caseLaserBeam(StackPane stackPane, GameElement gameElement) {
        switch (gameElement.orientations.get(0)){
            case LEFT,RIGHT -> {
                ImageView imageView01 = new ImageView(new Image("/LaserBeam.png"));
                stackPane.getChildren().add(imageView01);
                stackPane.setAlignment(imageView01, Pos.CENTER);
            }
            case TOP,BOTTOM -> {
                ImageView imageView01 = new ImageView(new Image("/LaserBeamI.png"));
                stackPane.getChildren().add(imageView01);
                stackPane.setAlignment(imageView01, Pos.CENTER);
            }
        }
        return;
    }
}
