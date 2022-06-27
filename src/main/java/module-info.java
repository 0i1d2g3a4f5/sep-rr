/**
 * @author Ringer
 */
module desperate.drosseln.hp {
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.json;
    requires javafx.controls;
    opens client_application to javafx.fxml;
    opens server_application to javafx.fxml;
    exports client_application;
    exports server_application;
}