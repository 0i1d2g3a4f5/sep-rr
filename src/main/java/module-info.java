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

    exports gamelogic;

    opens client_application to javafx.fxml;
    opens server_application to javafx.fxml;

    exports client_application;
    exports server_application;
}