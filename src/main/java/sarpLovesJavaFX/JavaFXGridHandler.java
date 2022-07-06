package sarpLovesJavaFX;


import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.Checkpoint;
import client_package.client_gamelogic.game_elements.ConveyorBelt;
import client_package.client_gamelogic.game_elements.GameElement;
import client_package.client_gamelogic.game_elements.Gear;
import client_package.client_gamelogic.game_elements.robot.Robot;
import client_package.client_gamelogic.map.GameBoard;
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

    public GridPane gridPaneFromCards(ArrayList<Card> cardList){
        try {
            return constructCardsList(cardList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public GridPane gridPaneFromGameBoard(GameBoard gameBoard){
        GridPane temp = updateGameBoard(gameBoard);
        temp.setScaleX(0.2);
        temp.setScaleY(0.2);
        return temp;
    }

    private void addToPane(StackPane stackPane,ImageView imageView){
        stackPane.getChildren().add(imageView);
        stackPane.setAlignment(imageView, Pos.CENTER);
    }

    /**
     * @author Vivian Kafadar
     *
     * @param cardList
     * @return
     * @throws IOException
     */
    private GridPane constructCardsList(ArrayList<Card> cardList) throws IOException {
        GridPane gridPane = new GridPane();
        // get card list length, add image in gridpanes at needed locations
        for(int i=0; i<cardList.size(); i++){
            StackPane stackPane = new StackPane();
                switch (cardList.get(i).getCardName()) {
                    // programming cards
                    case AGAIN:
                        // add card again
                        addToPane(stackPane, Images.AGAIN_CARD.toImageView());
                        break;
                    case BACK_UP:
                        addToPane(stackPane, Images.CARD_BACK_CARD.toImageView());
                        break;
                    case LEFT_TURN:
                        addToPane(stackPane, Images.TURN_LEFT_CARD.toImageView());
                        break;
                    case MOVE_ONE:
                        addToPane(stackPane, Images.MOVE_1_CARD.toImageView());
                        break;
                    case MOVE_THREE:
                        addToPane(stackPane, Images.MOVE_3_CARD.toImageView());
                        break;
                    case MOVE_TWO:
                        addToPane(stackPane, Images.MOVE_2_CARD.toImageView());
                        break;
                    case POWER_UP:
                        addToPane(stackPane, Images.POWER_UP_CARD.toImageView());
                        break;
                    case RIGHT_TURN:
                        addToPane(stackPane, Images.TURN_RIGHT_CARD.toImageView());
                        break;
                    case U_TURN:
                        addToPane(stackPane, Images.U_TURN_CARD.toImageView());
                        break;

                    // special programming cards
                    case ENERGY_ROUTINE:
                        addToPane(stackPane, Images.ENERGY_ROUTINE_CARD.toImageView());
                        break;
                    case REPEAT_ROUTINE:
                        addToPane(stackPane, Images.REPEAT_ROUTINE_CARD.toImageView());
                        break;
                    case SANDBOX_ROUTINE:
                        addToPane(stackPane, Images.SANDBOX_ROUTINE_CARD.toImageView());
                        break;
                    case SPAM_FOLDER:
                        addToPane(stackPane, Images.SPAM_FOLDER_CARD.toImageView());
                        break;
                    case WEASEL_ROUTINE:
                        addToPane(stackPane, Images.WEASEL_ROUTINE_CARD.toImageView());
                        break;

                    // damage cards
                    case SPAM:
                        addToPane(stackPane, Images.SPAM_CARD.toImageView());
                        break;
                    case TROJAN_HORSE:
                        addToPane(stackPane, Images.TROJAN_HORSE_CARD.toImageView());
                        break;
                    case VIRUS:
                        addToPane(stackPane, Images.VIRUS_CARD.toImageView());
                        break;
                    case WORM:
                        addToPane(stackPane, Images.WORM_CARD.toImageView());
                        break;
                }
                gridPane.add(stackPane, i, 0);

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

    private GridPane updateGameBoard(GameBoard gameBoard){
        GridPane input = new GridPane();
        System.out.println("X: Y:  " + gameBoard.getDimensionX() + gameBoard.getDimensionY());
        for(int y=0; y<gameBoard.getDimensionY(); y++){
            for(int x=0; x<gameBoard.getDimensionX(); x++){
                GameField temp = gameBoard.getGameField(y, x);
                StackPane stackPane = new StackPane();
                ImageView imageView= new ImageView(new Image("TBDtile.png"));
                stackPane.getChildren().add(imageView);
                stackPane.setAlignment(imageView, Pos.CENTER);
                for(GameElement gameElement : temp.getElements()){
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
        ImageView imageView10 = new ImageView(new Image("images/boardElements/Reboot.png"));
        stackPane.getChildren().add(imageView10);
        stackPane.setAlignment(imageView10, Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private static void caseAntenna(StackPane stackPane, GameElement gameElement) {
        switch (gameElement.orientations.get(0)){
            case NORTH -> {
                ImageView imageView9 = Images.ANTENNA_NORTH_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView9 = Images.ANTENNA_SOUTH_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView9 = Images.ANTENNA_WEST_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case EAST -> {
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
    private static void caseRobot(StackPane stackPane, Robot gameElement) {
        Robot robot = gameElement;
        switch (robot.getFigure()){
            case 1 -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotBlueSpin.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 2 -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotRedHulkx90.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 3 -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotGreenZoom.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 4 -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotOrangeTwonky.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 5 -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotPurpleHammer.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case 6-> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotYellowSmash.png"));
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
    private static void caseWall(StackPane stackPane, GameElement gameElement) {
        switch (gameElement.orientations.get(0)){
            case NORTH ->{
                ImageView imageView7 = Images.WALL_TOP_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView7 = Images.WALL_BOTTOM_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView7 = Images.WALL_LEFT_ELEMENT.toImageView();
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case EAST -> {
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
    private static void caseStartPoint(StackPane stackPane) {
        ImageView imageView6 = Images.STARTING_POINT_ELEMENT.toImageView();
        stackPane.getChildren().add(imageView6);
        stackPane.setAlignment(imageView6,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private static void casePit(StackPane stackPane) {
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
    private static void casePushPanel(StackPane stackPane, GameElement gameElement) throws IOException {
        switch(Game.getInstance().getActiveRegister()){
            case 1,3,5 -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_TOP_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_BOTTOM_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case EAST -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_RIGHT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView4 = Images.PUSH_PANEL_135_LEFT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                }
            }
            case 2,4 ->{
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView4 = Images.PUSH_PANEL_24_TOP_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView4 = Images.PUSH_PANEL_24_BOTTOM_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case EAST -> {
                        ImageView imageView4 = Images.PUSH_PANEL_24_RIGHT_ELEMENT.toImageView();
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case WEST -> {
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
    private static void caseGear(StackPane stackPane, Gear gameElement) {
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
    private static void caseEnergySpace(StackPane stackPane) {
        ImageView imageView2 = Images.ENERGY_SPACE_GREEN_ELEMENT.toImageView();
        stackPane.getChildren().add(imageView2);
        stackPane.setAlignment(imageView2,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private static void caseConveyorBelt(StackPane stackPane, GameElement gameElement) {
        ConveyorBelt conveyorBelt = (ConveyorBelt) gameElement;
        switch (conveyorBelt.getColor()){
            case BLUE -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltBlueUp.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltBlueDown.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case EAST-> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltBlueRight.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltBlueLeft.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                }
            }
            case GREEN -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltGreenUp.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltGreenDown.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case EAST-> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltGreenRight.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= new ImageView(new Image("images/boardElements/BeltGreenLeft.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
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
    private static void caseCheckpoint(StackPane stackPane, Checkpoint gameElement) {
        Checkpoint checkpoint = gameElement;
        System.out.println("ASDFGLJKFASDGKJSGDJKFKJSGD" + checkpoint.getCount());
        switch (checkpoint.getCount()){
            case 1 -> {
                ImageView imageViewcheck = new ImageView(new Image("images/boardElements/CheckPoint1.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 2 ->{
                ImageView imageViewcheck = new ImageView(new Image("images/boardElements/CheckPoint2.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 3->{
                ImageView imageViewcheck = new ImageView(new Image("images/boardElements/CheckPoint3.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 4->{
                ImageView imageViewcheck = new ImageView(new Image("images/boardElements/CheckPoint4.png"));
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
    private static void caseLaser(StackPane stackPane, GameElement gameElement) {
        switch(gameElement.orientations.get(0)) {
            case NORTH -> {
                ImageView imageView0 = new ImageView(new Image("images/boardElements/LaserUp.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView0 = new ImageView(new Image("images/boardElements/LaserDown.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0,Pos.CENTER);
            }
            case EAST -> {
                ImageView imageView0 = new ImageView(new Image("images/boardElements/LaserLeft.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView0 = new ImageView(new Image("images/boardElements/LaserRight.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
        }
        return;
    }

}
