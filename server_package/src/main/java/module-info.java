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

    exports server_package.gamelogic;

    opens server_package.server_application to javafx.fxml;


    exports server_package.server_application;
}