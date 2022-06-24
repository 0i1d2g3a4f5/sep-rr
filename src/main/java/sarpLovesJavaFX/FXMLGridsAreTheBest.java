package sarpLovesJavaFX;

import com.google.gson.JsonObject;
import gamelogic.game_elements.ConveyorBelt;
import gamelogic.game_elements.GameElement;
import gamelogic.map.GameBoard;
import gamelogic.map.GameField;
import gamelogic.map.MapCreator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import gamelogic.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
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
                            switch(gameElement.orientations.get(0)) {
                                case NORTH -> {
                                    ImageView imageView0 = new ImageView(new Image("images/LaserT.png"));
                                    stackPane.getChildren().add(imageView0);
                                    stackPane.setAlignment(imageView0, Pos.CENTER);
                                }
                                case SOUTH -> {
                                    ImageView imageView0 = new ImageView(new Image("images/LaserB.png"));
                                    stackPane.getChildren().add(imageView0);
                                    stackPane.setAlignment(imageView0,Pos.CENTER);
                                }
                                case EAST -> {
                                    ImageView imageView0 = new ImageView(new Image("images/LaserR.png"));
                                    stackPane.getChildren().add(imageView0);
                                    stackPane.setAlignment(imageView0, Pos.CENTER);
                                }
                                case WEST -> {
                                    ImageView imageView0 = new ImageView(new Image("images/LaserL.png"));
                                    stackPane.getChildren().add(imageView0);
                                    stackPane.setAlignment(imageView0, Pos.CENTER);
                                }
                            }
                            break;
                        case CHECKPOINT:
                            //count 1,2,3,4
                            break;
                        case CONVEYORBELT:
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
                            break;
                        case ENERGYSPACE:
                            ImageView imageView2 = new ImageView("images/energy.png");
                            stackPane.getChildren().add(imageView2);
                            stackPane.setAlignment(imageView2,Pos.CENTER);
                            break;
                        case GEAR:
                            // imageView3, clockwise or counterclockwise
                            break;
                        case PUSHPANEL:
                            // imageView4, register 1,3,5 or 2,4
                            break;
                        case PIT:
                            ImageView imageView5 = new ImageView("images/pit.png");
                            stackPane.getChildren().add(imageView5);
                            stackPane.setAlignment(imageView5,Pos.CENTER);
                            break;
                        case STARTPOINT:
                            ImageView imageView6 = new ImageView("images/startpoint.png");
                            stackPane.getChildren().add(imageView6);
                            stackPane.setAlignment(imageView6,Pos.CENTER);
                            break;
                        case WALL:
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
                            break;
                        case ROBOT:
                            //imageView 8, 6 Figure
                            break;
                        case ANTENNA:
                            //imageView9, orientations again
                            break;
                        case RESTARTPOINT:
                            ImageView imageView10 = new ImageView(new Image("images/LaserR.png"));
                            stackPane.getChildren().add(imageView10);
                            stackPane.setAlignment(imageView10, Pos.CENTER);
                            break;
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

}
