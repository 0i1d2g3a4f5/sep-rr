package aexperimental;

import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import gamelogic.cards.CardName;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sarpLovesJavaFX.JavaFXGridHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Test extends Application {
    TestController testController;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 810);
        testController=fxmlLoader.getController();
        testController.test=this;
        testController.init();
        stage.setScene(scene);
        stage.show();

    }

}
