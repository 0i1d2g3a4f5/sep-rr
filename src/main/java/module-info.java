/**
 * @author Ringer
 */
module desperate.drosseln.hp {
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.json;
    requires javafx.controls;
    requires deeplearning4j.datasets;
    requires deeplearning4j.nn;
    requires nd4j.api;

    opens client_application to javafx.fxml;
    opens server_application to javafx.fxml;
    exports client_application;
    exports server_application;
}