package aexperimental;

import client_package.client_gamelogic.map.GameBoard;
import javafx.scene.layout.GridPane;
import sarpLovesJavaFX.JavaFXGridHandler;

public class Bbbb {
    public void gpm(GridPane gridPane, GameBoard gameBoard){
        System.out.println(gameBoard.getDimensionX() + " " + gameBoard.getDimensionY());
/*
        gridPane.getChildren().clear();
        for(int x=0; x< gameBoard.getDimensionX(); x++){
            for(int y=0; y< gameBoard.getDimensionY(); y++){
                ImageView imageView = new ImageView(new Image("TBDtile.png"));
                gridPane.add(imageView, x, y);
                System.out.println(gridPane.getChildren().size());
            }
        }

 */
        JavaFXGridHandler jfg = new JavaFXGridHandler();
        gridPane =jfg.gridPaneFromGameBoard(gameBoard, 1);

    }
}
