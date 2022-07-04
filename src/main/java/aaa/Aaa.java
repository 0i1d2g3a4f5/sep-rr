package aaa;

import client_package.client_gamelogic.map.ModelLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Aaa extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage stage1 = new Stage();
        Bbbb bbbb = new Bbbb();
        GridPane gridPane = new GridPane();
        ModelLoader modelLoader = new ModelLoader();
        Scene scene = new Scene(gridPane, 1280, 720 );
        stage1.setScene(scene);
        stage1.show();

        bbbb.gpm((GridPane) scene.getRoot(), modelLoader.loadMap("dizzy_highway"));

    }
}
