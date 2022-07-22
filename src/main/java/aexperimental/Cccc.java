package aexperimental;

import client_application.ClientApplication;
import client_application.TaskHandler;
import client_package.Client;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.cards.CardFactory;
import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.game_elements.robot.Robot;
import client_package.client_gamelogic.map.GameBoard;
import client_package.client_gamelogic.map.ModelLoader;
import gamelogic.Direction;
import gamelogic.Position;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sarpLovesJavaFX.JavaFXGridHandler;


import java.util.ArrayList;
/**
 * @author Sarp Cagin Erdogan
 * @author Qinyi
 */
public class Cccc extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        JavaFXGridHandler javaFXGridHandler = new JavaFXGridHandler();
        Robot robot = new Robot(new Client(), new Position(0, 0), Direction.LEFT, 2);
        StackPane stackPane = new StackPane();
        javaFXGridHandler.caseRobot(stackPane, robot);
        Scene scene= new Scene(stackPane,1000, 1000);
        stage.setScene(scene);
        stage.show();

    }
}
