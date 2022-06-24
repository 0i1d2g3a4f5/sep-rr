package sarpLovesJavaFX;

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

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FXMLGridsAreTheBest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        Image tile = new Image("images/tile.png");
        Image zaaaa = new Image("images/checkenabled.png");
        List<StackPane> panes = new ArrayList<>();
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                ImageView imageView = new ImageView(tile);
                ImageView imageView1 = new ImageView(zaaaa);
                StackPane pane1 = new StackPane();
                pane1.getChildren().add(imageView);
                pane1.getChildren().add(imageView1);
                pane1.setAlignment(imageView, Pos.CENTER);
                pane1.setAlignment(imageView1, Pos.CENTER);
                panes.add(pane1);
                gridPane.add(panes.get(i*5+j), i, j, 1, 1);
            }
        }
        StackPane pane = new StackPane();
        ImageView temp = new ImageView(new Image("images/conveyorleft.png"));
        pane.getChildren().add(temp);
        pane.setAlignment(temp, Pos.CENTER);
        gridPane.getChildren().remove(24);
        gridPane.add(pane, 4,4);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

}
