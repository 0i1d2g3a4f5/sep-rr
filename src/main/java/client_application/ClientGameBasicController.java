package client_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ClientGameBasicController {

    public ClientApplication clientApplication;

    @FXML
    private GridPane activePlayerRegister;

    @FXML
    private Pane activePlayerRegisterPane;

    @FXML
    private ScrollPane activePlayerRegisterScroll;

    @FXML
    private Pane gameBoardPane;

    @FXML
    private ScrollPane gameBoardScroll;

    @FXML
    private ScrollPane programmingCardsScroll;

    @FXML
    private Pane programmingCardsScrollprogrammingCardsPane;

    @FXML
    private Pane registerOfOthersPane;

    @FXML
    private ScrollPane registerOfOthersScroll;

    @FXML
    void startButton(ActionEvent event) {

    }

}
