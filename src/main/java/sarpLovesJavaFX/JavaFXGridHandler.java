package sarpLovesJavaFX;


import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.Checkpoint;
import client_package.client_gamelogic.game_elements.ConveyorBelt;
import client_package.client_gamelogic.game_elements.GameElement;
import client_package.client_gamelogic.game_elements.Gear;
import client_package.client_gamelogic.game_elements.robot.Robot;
import client_package.client_gamelogic.map.GameBoard;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import client_package.client_gamelogic.*;
import client_package.client_gamelogic.map.*;
import utility.ImagePathFromName;

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
        return updateGameBoard(gameBoard);
    }

    private void addToPane(StackPane stackPane,String path){
        ImageView imageView1= new ImageView(new Image(path));
        stackPane.getChildren().add(imageView1);
        stackPane.setAlignment(imageView1, Pos.CENTER);
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
                    addToPane(stackPane, ImagePathFromName.AGAIN.toString());
                    break;
                case BACK_UP:
                    addToPane(stackPane, ImagePathFromName.BACK_UP.toString());
                    break;
                case LEFT_TURN:
                    addToPane(stackPane,ImagePathFromName.LEFT_TURN.toString());
                    break;
                case MOVE_ONE:
                    addToPane(stackPane, ImagePathFromName.MOVE_ONE.toString());
                    break;
                case MOVE_THREE:
                    addToPane(stackPane, ImagePathFromName.MOVE_THREE.toString());
                    break;
                case MOVE_TWO:
                    addToPane(stackPane, ImagePathFromName.MOVE_TWO.toString());
                    break;
                case POWER_UP:
                    addToPane(stackPane, ImagePathFromName.POWER_UP.toString());
                    break;
                case RIGHT_TURN:
                    addToPane(stackPane, ImagePathFromName.RIGHT_TURN.toString());
                    break;
                case U_TURN:
                    addToPane(stackPane, ImagePathFromName.U_TURN.toString());
                    break;

                // special programming cards
                case ENERGY_ROUTINE:
                    addToPane(stackPane, "images/cards/ProgrammingCards/EnergyRoutine.png");
                    break;
                case REPEAT_ROUTINE:
                    addToPane(stackPane, "images/cards/ProgrammingCards/RepeatRoutine.png");
                    break;
                case SANDBOX_ROUTINE:
                    addToPane(stackPane, "images/cards/ProgrammingCards/SandboxRoutine.png");
                    break;
                case SPAM_FOLDER:
                    addToPane(stackPane, "images/cards/ProgrammingCards/SpamFolder.png");
                    break;
                case WEASEL_ROUTINE:
                    addToPane(stackPane, "images/cards/ProgrammingCards/WeaselRoutine.png");
                    break;

                // damage cards
                case SPAM:
                    addToPane(stackPane, "images/cards/ProgrammingCards/Spam.png");
                    break;
                case TROJAN_HORSE:
                    addToPane(stackPane, "images/cards/ProgrammingCards/TrojanHorse.png");
                    break;
                case VIRUS:
                    addToPane(stackPane, "images/cards/ProgrammingCards/Virus.png");
                    break;
                case WORM:
                    addToPane(stackPane, "images/cards/ProgrammingCards/StackPane.png");
                    break;
                }
                gridPane.add(stackPane,i,0);
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
    private void constructMap(GridPane gridPane) throws IOException {
        Game game = Game.getInstance();
        ScrollPane scrollPane = new ScrollPane();
        for(int j=0; j<game.getMap().getDimensionY(); j++){
            for(int i=0; i<game.getMap().getDimensionX(); i++){
                GameField temp = game.getMap().getGameField(j, i);
                StackPane stackPane = new StackPane();
                ImageView imageView= new ImageView(new Image("images/boardElements/TBDtile.png"));
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
                            casePushPanel(stackPane, gameElement);
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
                gridPane.add(stackPane, i, j);
            }
        }
        scrollPane.setContent(gridPane);
        Scene scene = new Scene(scrollPane, 512, 512);
    }
    private GridPane updateGameBoard(GameBoard gameBoard){
        GridPane input = new GridPane();
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
    public static Scene fromMap(GameBoard gameBoard) throws IOException {
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        for(int j=0; j<gameBoard.getDimensionY(); j++){
            for(int i=0; i<gameBoard.getDimensionX(); i++){
                GameField temp = gameBoard.getGameField(j, i);
                StackPane stackPane = new StackPane();
                ImageView imageView= new ImageView(new Image("images/boardElements/TBDtile.png"));
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
                            casePushPanel(stackPane, gameElement);
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
                gridPane.add(stackPane, i, j);
            }
        }
        scrollPane.setContent(gridPane);
        Scene scene = new Scene(scrollPane, 512, 512);
        return scene;

    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private static void caseRestartPoint(StackPane stackPane) {
        ImageView imageView10 = new ImageView(new Image("Reboot.png"));
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
                ImageView imageView9 = new ImageView(new Image("AntennaNorth.png"));
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView9 = new ImageView(new Image("AntennaSouth.png"));
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView9 = new ImageView(new Image("AntennaWest.png"));
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case EAST -> {
                ImageView imageView9 = new ImageView(new Image("AntennaEast.png"));
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
        switch (robot.getColor()){
            case BLUE -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotBlueSpin.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case RED -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotRedHulkx90.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case GREEN -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotGreenZoom.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case ORANGE -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotOrangeTwonky.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case PURPLE -> {
                ImageView imageView8 = new ImageView(new Image("images/robots/RobotPurpleHammer.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case YELLOW -> {
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
                ImageView imageView7 = new ImageView("WallTop.png");
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView7 = new ImageView("WallBottom.png");
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView7 = new ImageView("WallLeft.png");
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case EAST -> {
                ImageView imageView7 = new ImageView("WallRight.png");
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
        ImageView imageView6 = new ImageView("StartingPoint.png");
        stackPane.getChildren().add(imageView6);
        stackPane.setAlignment(imageView6,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private static void casePit(StackPane stackPane) {
        ImageView imageView5 = new ImageView("Pit.png");
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
                        ImageView imageView4 = new ImageView("PushPanel125Bottom.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView4 = new ImageView("PushPanel135Top.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case EAST -> {
                        ImageView imageView4 = new ImageView("PushPanel135Left.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView4 = new ImageView("PushPanel135Right.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                }
            }
            case 2,4 ->{
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView4 = new ImageView("PushPanel24Bottom.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView4 = new ImageView("PushPanel24Top.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case EAST -> {
                        ImageView imageView4 = new ImageView("PushPanel24Left.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView4 = new ImageView("PushPanel24Right.png");
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
                ImageView imageView3 = new ImageView("GearClockwise.png");
                stackPane.getChildren().add(imageView3);
                stackPane.setAlignment(imageView3,Pos.CENTER);
            }
            case COUNTERCLOCKWISE -> {
                ImageView imageView3 = new ImageView("GearCounterclockwise.png");
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
        ImageView imageView2 = new ImageView("EnergySpaceGreen.png");
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
                        ImageView imageView1= new ImageView(new Image("BeltBlueUp.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView1= new ImageView(new Image("BeltBlueDown.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case EAST-> {
                        ImageView imageView1= new ImageView(new Image("BeltBlueRight.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= new ImageView(new Image("BeltBlueLeft.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                }
            }
            case GREEN -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView1= new ImageView(new Image("BeltGreenUp.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView1= new ImageView(new Image("BeltGreenDown.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case EAST-> {
                        ImageView imageView1= new ImageView(new Image("BeltGreenRight.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= new ImageView(new Image("BeltGreenLeft.png"));
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
        switch (checkpoint.getCount()){
            case 1 -> {
                ImageView imageViewcheck = new ImageView(new Image("CheckPoint1.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 2 ->{
                ImageView imageViewcheck = new ImageView(new Image("CheckPoint2.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 3->{
                ImageView imageViewcheck = new ImageView(new Image("CheckPoint3.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 4->{
                ImageView imageViewcheck = new ImageView(new Image("CheckPoint4.png"));
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
                ImageView imageView0 = new ImageView(new Image("LaserUp.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView0 = new ImageView(new Image("LaserDown.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0,Pos.CENTER);
            }
            case EAST -> {
                ImageView imageView0 = new ImageView(new Image("LaserLeft.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView0 = new ImageView(new Image("LaserRight.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
        }
        return;
    }

}
