package aaa;

import client_package.client_gamelogic.map.ModelLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Aaa extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage stage1 = new Stage();
        Bbbb bbbb = new Bbbb();
        ScrollPane scrollPane = new ScrollPane();
        GridPane gridPane = new GridPane();
        ModelLoader modelLoader = new ModelLoader();
        scrollPane.setContent(gridPane);
        Scene scene = new Scene(scrollPane, 1280, 720 );
        stage1.setScene(scene);
        stage1.show();
        ScrollPane scrollPane1 = (ScrollPane) scene.getRoot();
        GridPane temp = (GridPane) scrollPane1.getContent();

        //bbbb.gpm(temp, modelLoader.loadMap("dizzy_highway"));

    }
}
