/**
 * @author Ringer
 */
module desperate.drosseln.hp {

    /*
    requires deeplearning4j.nn;
    requires nd4j.api;
    */


    requires neat;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.controls;
    requires log4j;

    exports gamelogic;
    exports client_package.client_gamelogic;
    exports gamelogic.game_elements;
    exports client_package.client_gamelogic.map;

    opens client_application to javafx.fxml;
    opens server_application to javafx.fxml;
    opens sarpLovesJavaFX to javafx.fxml;
    opens aexperimental to javafx.fxml, javafx.graphics;

    exports client_application;
    exports server_application;
    exports sarpLovesJavaFX;
    exports aexperimental;
}