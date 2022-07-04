package client_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ClientGameBasicController {

    public ClientApplication clientApplication;

    @FXML
    private GridPane gridAvailableProgramming;

    @FXML
    private GridPane gridGameBoard;

    @FXML
    private GridPane gridOtherRegisters;

    @FXML
    private GridPane gridProgramming;

    @FXML
    private GridPane gridTempProgramming;

    @FXML
    void startButton(ActionEvent event) {

    }
    public void updateProgrammingCards(GridPane gridPane){
        gridProgramming.getChildren().clear();
        gridProgramming=gridPane;
    }
    public void updateOtherRegisters(GridPane gridPane){
        gridOtherRegisters.getChildren().clear();
        gridOtherRegisters=gridPane;
    }

    public void updateOwnRegister(GridPane gridPane){
        gridOtherRegisters.getChildren().clear();
        gridOtherRegisters=gridPane;
    }



    public void updateGameBoard(GridPane gridPane){
        gridGameBoard.getChildren().clear();
        gridGameBoard=gridPane;
    }

}
