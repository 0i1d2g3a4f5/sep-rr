package aaa;

import client_package.client_gamelogic.map.GameBoard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Bbbb {
    public void gpm(GridPane gridPane, GameBoard gameBoard){
        System.out.println(gameBoard.getDimensionX() + " " + gameBoard.getDimensionY());

        gridPane.getChildren().clear();
        for(int x=0; x< gameBoard.getDimensionX(); x++){
            for(int y=0; y< gameBoard.getDimensionY(); y++){
                ImageView imageView = new ImageView(new Image("TBDtile.png"));
                gridPane.add(imageView, x, y);
                System.out.println(gridPane.getChildren().size());
            }
        }

    }
}
