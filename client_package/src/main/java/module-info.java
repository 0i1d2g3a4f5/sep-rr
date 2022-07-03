/**
 * @author Ringer
 */
module desperate.drosseln.hp {

    requires neat;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.controls;


    opens client_package.client_application to javafx.fxml;


    exports client_package.client_application;

}