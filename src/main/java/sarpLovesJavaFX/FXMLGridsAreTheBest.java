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
        GridPane gridPane = new GridPane();
        for(int j=0; j<gameBoard.getDimensionY(); j++){
            for(int i=0; i<gameBoard.getDimensionX(); i++){
                GameField temp = gameBoard.getGameField(j, i);
                StackPane stackPane = new StackPane();
                ImageView mutterficker= new ImageView(new Image("images/tile.png"));
                stackPane.getChildren().add(mutterficker);
                stackPane.setAlignment(mutterficker, Pos.CENTER);

                for(GameElement gameElement : temp.getElements()){
                    switch (gameElement.getType()){
                        case LASER:
                            break;
                        case CHECKPOINT:
                            break;
                        case CONVEYORBELT:
                            switch (gameElement.orientations.get(0)){

                                case NORTH -> {
                                    ImageView mutterficker2= new ImageView(new Image("images/conveyorup.png"));
                                    stackPane.getChildren().add(mutterficker2);
                                    stackPane.setAlignment(mutterficker2, Pos.CENTER);
                                }
                                case SOUTH -> {
                                    ImageView mutterficker2= new ImageView(new Image("images/conveyordown.png"));
                                    stackPane.getChildren().add(mutterficker2);
                                    stackPane.setAlignment(mutterficker2, Pos.CENTER);
                                }
                                case EAST-> {
                                    ImageView mutterficker2= new ImageView(new Image("images/conveyorright.png"));
                                    stackPane.getChildren().add(mutterficker2);
                                    stackPane.setAlignment(mutterficker2, Pos.CENTER);
                                }
                                case WEST -> {
                                    ImageView mutterficker2= new ImageView(new Image("images/conveyorleft.png"));
                                    stackPane.getChildren().add(mutterficker2);
                                    stackPane.setAlignment(mutterficker2, Pos.CENTER);
                                }
                            }
                            break;
                        case ENERGYSPACE:
                            break;
                        case GEAR:
                            break;
                        case PUSHPANEL:
                            break;
                        case PIT:
                            break;
                        case STARTPOINT:
                            break;
                        case WALL:
                            break;
                        case ROBOT:
                            break;
                        case ANTENNA:
                            break;
                        case RESTARTPOINT:
                            break;
                        case EMPTY:



                            break;
                    }
                }
                gridPane.add(stackPane, i, j);
            }
        }
        gridPane.setScaleX(0.5);
        gridPane.setScaleY(0.5);
        Scene scene = new Scene(gridPane);
        return scene;

    }

}
