package sarpLovesJavaFX;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FXMLGridsAreTheBest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        Image tile = new Image("tile.png");
        List<Node> board = new ArrayList<>();
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                ImageView imageView = new ImageView(tile);
                board.add(imageView);
                gridPane.add(board.get(i*5+j), i, j, 1, 1);
            }
        }
        ImageView temp = (ImageView) gridPane.getChildren().get(12);
        temp.setImage(new Image("checkenabled.png"));
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
