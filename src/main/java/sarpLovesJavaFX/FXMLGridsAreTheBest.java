package sarpLovesJavaFX;

import gamelogic.game_elements.*;
import gamelogic.game_elements.robot.Robot;
import gamelogic.map.GameBoard;
import gamelogic.map.GameField;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import gamelogic.*;

/**
 * @author Sarp Cagin Erdogan
 * @author Qinyi
 */

public class FXMLGridsAreTheBest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    }
    void rotate(ImageView imageView,int direction){
        imageView.setRotate(imageView.getRotate()+direction*90);
    }

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

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param gameBoard
     * @return
     */
    public static Scene fromMap(GameBoard gameBoard){
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        for(int j=0; j<gameBoard.getDimensionY(); j++){
            for(int i=0; i<gameBoard.getDimensionX(); i++){
                GameField temp = gameBoard.getGameField(j, i);
                StackPane stackPane = new StackPane();
                ImageView imageView= new ImageView(new Image("images/tile.png"));
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
        ImageView imageView10 = new ImageView(new Image("images/restart.png"));
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
                ImageView imageView9 = new ImageView(new Image("images/antennanorth.png"));
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView9 = new ImageView(new Image("images/antennasouth.png"));
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView9 = new ImageView(new Image("images/antennawest.png"));
                stackPane.getChildren().add(imageView9);
                stackPane.setAlignment(imageView9, Pos.CENTER);
            }
            case EAST -> {
                ImageView imageView9 = new ImageView(new Image("images/antennaeast.png"));
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
                ImageView imageView8 = new ImageView(new Image("images/roboblue.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case RED -> {
                ImageView imageView8 = new ImageView(new Image("images/robored.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case GREEN -> {
                ImageView imageView8 = new ImageView(new Image("images/robogreen.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case ORANGE -> {
                ImageView imageView8 = new ImageView(new Image("images/roboorange.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case PURPLE -> {
                ImageView imageView8 = new ImageView(new Image("images/robopurple.png"));
                stackPane.getChildren().add(imageView8);
                stackPane.setAlignment(imageView8, Pos.CENTER);
            }
            case YELLOW -> {
                ImageView imageView8 = new ImageView(new Image("images/roboyellow.png"));
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
                ImageView imageView7 = new ImageView("images/wallT.png");
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView7 = new ImageView("images/wallB.png");
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView7 = new ImageView("images/wallL.png");
                stackPane.getChildren().add(imageView7);
                stackPane.setAlignment(imageView7,Pos.CENTER);
            }
            case EAST -> {
                ImageView imageView7 = new ImageView("images/wallR.png");
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
        ImageView imageView6 = new ImageView("images/startpoint.png");
        stackPane.getChildren().add(imageView6);
        stackPane.setAlignment(imageView6,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     */
    private static void casePit(StackPane stackPane) {
        ImageView imageView5 = new ImageView("images/pit.png");
        stackPane.getChildren().add(imageView5);
        stackPane.setAlignment(imageView5,Pos.CENTER);
        return;
    }

    /**
     * @author Sarp Cagin Erdogan, Qinyi, Mark Ringer
     * @param stackPane
     * @param gameElement
     */
    private static void casePushPanel(StackPane stackPane, GameElement gameElement) {
        switch(Game.getInstance().getActiveRegister()){
            case 1,3,5 -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView4 = new ImageView("images/push135B.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView4 = new ImageView("images/push135T.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case EAST -> {
                        ImageView imageView4 = new ImageView("images/push135L.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView4 = new ImageView("images/push135R.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                }
            }
            case 2,4 ->{
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView4 = new ImageView("images/push24B.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView4 = new ImageView("images/push24T.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case EAST -> {
                        ImageView imageView4 = new ImageView("images/push24L.png");
                        stackPane.getChildren().add(imageView4);
                        stackPane.setAlignment(imageView4,Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView4 = new ImageView("images/push24R.png");
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
                ImageView imageView3 = new ImageView("images/clockwisegear.png");
                stackPane.getChildren().add(imageView3);
                stackPane.setAlignment(imageView3,Pos.CENTER);
            }
            case COUNTERCLOCKWISE -> {
                ImageView imageView3 = new ImageView("images/conterclockwise.png");
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
        ImageView imageView2 = new ImageView("images/energy.png");
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
                        ImageView imageView1= new ImageView(new Image("images/bluebelt.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView1= new ImageView(new Image("images/bluebeltdown.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case EAST-> {
                        ImageView imageView1= new ImageView(new Image("images/bluebeltR.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= new ImageView(new Image("images/bluebeltL.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                }
            }
            case GREEN -> {
                switch (gameElement.orientations.get(0)){
                    case NORTH -> {
                        ImageView imageView1= new ImageView(new Image("images/greenbelt.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case SOUTH -> {
                        ImageView imageView1= new ImageView(new Image("images/greenbeltdown.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case EAST-> {
                        ImageView imageView1= new ImageView(new Image("images/greenbeltR.png"));
                        stackPane.getChildren().add(imageView1);
                        stackPane.setAlignment(imageView1, Pos.CENTER);
                    }
                    case WEST -> {
                        ImageView imageView1= new ImageView(new Image("images/greenbeltL.png"));
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
                ImageView imageViewcheck = new ImageView(new Image("images/checkPoint1.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 2 ->{
                ImageView imageViewcheck = new ImageView(new Image("images/checkPoint2.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 3->{
                ImageView imageViewcheck = new ImageView(new Image("images/checkPoint3.png"));
                stackPane.getChildren().add(imageViewcheck);
                stackPane.setAlignment(imageViewcheck, Pos.CENTER);
            }
            case 4->{
                ImageView imageViewcheck = new ImageView(new Image("images/checkPoint4.png"));
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
                ImageView imageView0 = new ImageView(new Image("images/LaserB.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case SOUTH -> {
                ImageView imageView0 = new ImageView(new Image("images/LaserT.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0,Pos.CENTER);
            }
            case EAST -> {
                ImageView imageView0 = new ImageView(new Image("images/LaserL.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
            case WEST -> {
                ImageView imageView0 = new ImageView(new Image("images/LaserR.png"));
                stackPane.getChildren().add(imageView0);
                stackPane.setAlignment(imageView0, Pos.CENTER);
            }
        }
        return;
    }

}
